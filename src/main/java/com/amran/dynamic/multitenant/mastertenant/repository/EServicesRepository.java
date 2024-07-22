package com.amran.dynamic.multitenant.mastertenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.amran.dynamic.multitenant.mastertenant.entity.EServices;



//@Repository
public interface EServicesRepository extends JpaRepository<EServices, Long>, JpaSpecificationExecutor<EServices> {

}