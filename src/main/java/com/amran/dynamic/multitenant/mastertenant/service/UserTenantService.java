package com.amran.dynamic.multitenant.mastertenant.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.amran.dynamic.multitenant.dto.UserDto;
import com.amran.dynamic.multitenant.mastertenant.userUtils.Role;
import com.amran.dynamic.multitenant.mastertenant.userUtils.UserAlreadyExistsException;

public interface UserTenantService {

	public ResponseEntity<UserDto> saveUser(UserDto user)throws UserAlreadyExistsException;

	public ResponseEntity<UserDto> findById(Long id);
	
	public ResponseEntity<UserDto> editUser(UserDto dto) throws UserAlreadyExistsException;
	
	public ResponseEntity<Void> delete(Long id);
	
	public ResponseEntity<Void> resetPassword(UserDto dto);
	
	public ResponseEntity<Void> deactivateUser(Long id);
	
	public ResponseEntity<List<UserDto>> listUserSimple();
	
	public ResponseEntity<List<Role>> listRole();

}
