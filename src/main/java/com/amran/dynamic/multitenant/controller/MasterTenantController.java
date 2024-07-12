package com.amran.dynamic.multitenant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amran.dynamic.multitenant.mastertenant.entity.MasterTenant;
import com.amran.dynamic.multitenant.mastertenant.service.MasterTenantService;


@RestController
@RequestMapping("/master")
public class MasterTenantController {

    @Autowired
    private MasterTenantService masterTenantService;

    @GetMapping("/getAllTenants")
    public ResponseEntity<List<MasterTenant>> getAllMasterTenants() {
        List<MasterTenant> tenants = masterTenantService.getAllMasterTenants();
        return ResponseEntity.ok(tenants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterTenant> getMasterTenantById(@PathVariable Integer id) {
        MasterTenant tenant = masterTenantService.findByClientId(id);
        if (tenant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tenant);
    }

    @PostMapping("/save")
    public ResponseEntity<MasterTenant> saveMasterDb(@RequestBody MasterTenant master) {
        MasterTenant save = masterTenantService.save(master);
        return ResponseEntity.ok(save);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MasterTenant> updateMasterTenant(@PathVariable Integer id,
                                                           @RequestBody MasterTenant masterTenantDetails) {
        return masterTenantService.updateMasterTenant(id, masterTenantDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMasterTenant(@PathVariable Integer id) {
        return masterTenantService.deleteMasterTenant(id);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllMasterTenants() {
        return masterTenantService.deleteAllMasterTenants();
    }
}
