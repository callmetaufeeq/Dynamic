package com.amran.dynamic.multitenant.controller;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.amran.dynamic.multitenant.dto.UserDto;
import com.amran.dynamic.multitenant.mastertenant.service.UserTenantService;
import com.amran.dynamic.multitenant.mastertenant.userUtils.Role;
import com.amran.dynamic.multitenant.mastertenant.userUtils.UserAlreadyExistsException;

@Validated
@RestController
@RequestMapping("/user")
public class UserTenantController {

    @Autowired
    private UserTenantService userTenantService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto dto) throws UserAlreadyExistsException {
        try {
            ResponseEntity<UserDto> response = userTenantService.saveUser(dto);
            return response;
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            ResponseEntity<UserDto> response = userTenantService.findById(id);
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching the user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editUser(@Valid @RequestBody UserDto dto) throws UserAlreadyExistsException {
        try {
            ResponseEntity<UserDto> response = userTenantService.editUser(dto);
            return response;
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while editing the user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            ResponseEntity<Void> response = userTenantService.delete(id);
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting the user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody UserDto dto) {
        try {
            ResponseEntity<Void> response = userTenantService.resetPassword(dto);
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while resetting the password", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateUser(@PathVariable Long id) {
        try {
            ResponseEntity<Void> response = userTenantService.deactivateUser(id);
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deactivating the user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> listUserSimple() {
        try {
            ResponseEntity<List<UserDto>> response = userTenantService.listUserSimple();
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while listing the users", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/roles")
    public ResponseEntity<?> listRoles() {
        try {
            ResponseEntity<List<Role>> response = userTenantService.listRole();
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while listing the roles", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
