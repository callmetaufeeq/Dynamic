package com.amran.dynamic.multitenant.mastertenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amran.dynamic.multitenant.mastertenant.entity.MasterTenant;

/**
 * @author 
 */
//@Repository
public interface MasterTenantRepository extends JpaRepository<MasterTenant, Integer> {
    MasterTenant findByTenantClientId(Integer clientId);
}
