package com.amran.dynamic.multitenant.mastertenant.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.amran.dynamic.multitenant.mastertenant.entity.MasterTenant;

/**
 * @author Md. Amran Hossain
 */
public interface MasterTenantService {

    MasterTenant findByClientId(Integer clientId);
    
    public List<MasterTenant> getAllMasterTenants();
    
    public MasterTenant save(MasterTenant master);
    
    public ResponseEntity<MasterTenant> updateMasterTenant(Integer id, MasterTenant masterTenantDetails);
    
    public ResponseEntity<Void> deleteMasterTenant(Integer id);
    
    public ResponseEntity<Void> deleteAllMasterTenants(); 
}
