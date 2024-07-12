package com.amran.dynamic.multitenant.mastertenant.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amran.dynamic.multitenant.constant.Constant;
import com.amran.dynamic.multitenant.mastertenant.config.AppConfigProperties;
import com.amran.dynamic.multitenant.mastertenant.entity.DictionaryCount;
import com.amran.dynamic.multitenant.mastertenant.entity.MasterTenant;
import com.amran.dynamic.multitenant.mastertenant.repository.DictionaryRepository;
import com.amran.dynamic.multitenant.mastertenant.repository.MasterTenantRepository;

@Service
public class MasterTenantServiceImpl implements MasterTenantService {

	private static final Logger LOG = LoggerFactory.getLogger(MasterTenantServiceImpl.class);

	@Autowired
	MasterTenantRepository masterTenantRepository;
	
	@Autowired
    private AppConfigProperties appConfigProperties;
	
	@Autowired
	private DictionaryRepository dictionaryRepo;

	// @Autowired
	// private DataSource dataSource;

	@Value("${multitenancy.mtapp.tenant.datasource.driverClassName}")
	private String driverClassName;

	@Value("${multitenancy.mtapp.tenant.datasource.username}")
	private String defaultUsername;

	@Value("${multitenancy.mtapp.tenant.datasource.password}")
	private String defaultPassword;

	@Value("${multitenancy.mtapp.master.datasource.url}")
	private String masterDbUrl;
	
	@Value("${multitenancy.mtapp.tenant.datasource.url}")
	private String tenantDbUrl;

	@Override
	public MasterTenant findByClientId(Integer clientId) {
		LOG.info("findByClientId() method call...");
		return masterTenantRepository.findByTenantClientId(clientId);
	}

	@Override
	public List<MasterTenant> getAllMasterTenants() {
		return masterTenantRepository.findAll();
	}

	@Override
	public MasterTenant save(MasterTenant m) {
		// Use tenant name to prefix the dbName
		List<String> modules = appConfigProperties.getModules();
		if(modules!=null) {
			DictionaryCount dic = dictionaryRepo.getByType(Constant.TENANT_NO);
			for (String each : modules) {
				MasterTenant master=new MasterTenant();
				String uniqueDbName = "tenant_" + dic.getCount() + "_" + m.getDbName()+"_"+each;
				master.setDbName(uniqueDbName);
				master.setDriverClass(driverClassName);
				master.setPassword(defaultPassword);
				master.setStatus("ACTIVE");
				master.setUrl(tenantDbUrl+uniqueDbName+"?useSSL=false&allowPublicKeyRetrieval=true");
				master.setUserName(defaultUsername);
				master.setTenantName(m.getTenantName());
				master.setTenantId(dic.getCount());
				MasterTenant savedTenant = masterTenantRepository.save(master);
				createTenantDatabase(savedTenant);
			}
			dic.setCount(dic.getCount() + 1);
			dictionaryRepo.save(dic);
		}else {
		}
		return null;
	}

	private void createTenantDatabase(MasterTenant masterTenant) {
		String dbName = masterTenant.getDbName();
		//String tenantDbUrl = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&allowPublicKeyRetrieval=true";
		try (Connection connection = DriverManager.getConnection(masterDbUrl, defaultUsername, defaultPassword);
		     Statement statement = connection.createStatement()) {
			// Create the new tenant database
			statement.executeUpdate("CREATE DATABASE " + dbName);
			LOG.info("Database {} created successfully", dbName);
		} catch (SQLException e) {
			LOG.error("Failed to create database", e);
		}
		// Update the master tenant with tenant-specific connection details
//		masterTenant.setUrl(tenantDbUrl);
//		masterTenant.setDriverClass(driverClassName);
//		masterTenant.setUserName(defaultUsername);
//		masterTenant.setPassword(defaultPassword);
//		masterTenantRepository.save(masterTenant);
		// Automatically create the schema for the new tenant database
		createTenantSchema(masterTenant.getUrl());
	}

	@Transactional
	private void createTenantSchema(String url) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setDataSource(createDataSource(url));
		entityManagerFactoryBean.setPackagesToScan("com.amran.dynamic.multitenant.tenant.entity");
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "update");
		entityManagerFactoryBean.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		entityManagerFactoryBean.afterPropertiesSet();

		EntityManagerFactory entityManagerFactory = entityManagerFactoryBean.getObject();
		if (entityManagerFactory != null) {
			entityManagerFactory.createEntityManager().close();
		}
	}

	private DataSource createDataSource(String url) {
		com.zaxxer.hikari.HikariConfig config = new com.zaxxer.hikari.HikariConfig();
		config.setJdbcUrl(url);
		config.setUsername(defaultUsername);
		config.setPassword(defaultPassword);
		config.setMaximumPoolSize(250);
		config.setConnectionTimeout(20000);
		config.setIdleTimeout(300000);
		config.setMinimumIdle(5);
		config.setDriverClassName(driverClassName);
		return new com.zaxxer.hikari.HikariDataSource(config);
	}

	@Override
	public ResponseEntity<MasterTenant> updateMasterTenant(Integer id, MasterTenant masterTenantDetails) {
		Optional<MasterTenant> masterTenantOptional = masterTenantRepository.findById(id);
		if (masterTenantOptional.isPresent()) {
			MasterTenant masterTenant = masterTenantOptional.get();
			masterTenant.setDbName(masterTenantDetails.getDbName());
			masterTenant.setTenantName(masterTenantDetails.getTenantName());
			masterTenant.setUrl(masterTenantDetails.getUrl());
			masterTenant.setUserName(masterTenantDetails.getUserName());
			masterTenant.setPassword(masterTenantDetails.getPassword());
			masterTenant.setDriverClass(masterTenantDetails.getDriverClass());
			masterTenant.setStatus(masterTenantDetails.getStatus());
			final MasterTenant updatedMasterTenant = masterTenantRepository.save(masterTenant);
			return ResponseEntity.ok(updatedMasterTenant);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<Void> deleteMasterTenant(Integer id) {
		Optional<MasterTenant> masterTenantOptional = masterTenantRepository.findById(id);
		if (masterTenantOptional.isPresent()) {
			masterTenantRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<Void> deleteAllMasterTenants() {
		masterTenantRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}
}
