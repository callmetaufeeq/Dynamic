package com.amran.dynamic.multitenant.mastertenant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amran.dynamic.multitenant.mastertenant.entity.UserTenant;

public interface UserTenantRepository extends JpaRepository<UserTenant, Long>{

	public UserTenant findOneById(Long id);
	
	public Optional<UserTenant> findOneByFirstName(String firstName);
	
	//public Boolean existsByFirstName(String firstName);
	
	public UserTenant findByFirstNameAndPassword(String firstName, String password);
}
