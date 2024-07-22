package com.amran.dynamic.multitenant.mastertenant.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amran.dynamic.multitenant.dto.UserDto;
import com.amran.dynamic.multitenant.dto.UserDtoConverter;
import com.amran.dynamic.multitenant.mastertenant.entity.UserTenant;
import com.amran.dynamic.multitenant.mastertenant.repository.RoleRepository;
import com.amran.dynamic.multitenant.mastertenant.repository.UserTenantRepository;
import com.amran.dynamic.multitenant.mastertenant.userUtils.Role;
import com.amran.dynamic.multitenant.mastertenant.userUtils.StatusType;
import com.amran.dynamic.multitenant.mastertenant.userUtils.UserAlreadyExistsException;

@Service
public class UserTenantServiceImpl implements UserTenantService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserTenantRepository userTenantRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public ResponseEntity<UserDto> saveUser(UserDto dto) throws UserAlreadyExistsException {

		logger.info("Creating user: " + dto);

		Optional<UserTenant> userOpt = userTenantRepository.findOneByFirstName(dto.getFirstName());
		if (userOpt.isPresent()) {
			logger.info("User's FirstName already exists. ");
			throw new UserAlreadyExistsException();
		}
		UserTenant user = new UserDtoConverter().apply(dto);
		
		List<Role> roles = roleRepository.findByRoleIn(Arrays.asList(dto.getRoles()));
	    if (roles.isEmpty()) {
	        logger.error("Roles not found: " + Arrays.toString(dto.getRoles()));
	        throw new RuntimeException("Roles not found: " + Arrays.toString(dto.getRoles()));
	    }
	    user.setRoles(roles);

		user.setPassword(encoder.encode(dto.getPassword()));
		user.setCreated(LocalDateTime.now());
		user.setModified(LocalDateTime.now());
		user.setJoinDate(LocalDateTime.now());
		userTenantRepository.save(user);

		logger.info(
				String.format("User %s has been created successfully", user.getFirstName() + " " + user.getLastName()));

		// Convert the saved User entity back to UserDto
		UserDto savedUserDto = new UserDtoConverter().apply(user);
		return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<UserDto> findById(Long id) {
		Optional<UserTenant> userOpt = userTenantRepository.findById(id);
		if (userOpt.isPresent()) {
			UserTenant user = userOpt.get();
			UserDto dto = new UserDto();

			if (user.getRoles() != null && !user.getRoles().isEmpty()) {
				String[] rolesArray = user.getRoles().stream().map(role -> role.getRole()) // Assuming Role has a method
																							// to get the role name
						.toArray(String[]::new);
				dto.setRoles(rolesArray);
			}

			BeanUtils.copyProperties(user, dto);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<UserDto> editUser(UserDto dto) throws UserAlreadyExistsException {
		logger.info("Editing user: " + dto);
		Optional<UserTenant> userOpt = userTenantRepository.findById(dto.getId());
		if (userOpt.isPresent()) {
			UserTenant user = userOpt.get();
			if (!dto.getFirstName().equals(user.getFirstName())) {
				Optional<UserTenant> existingUserOpt = userTenantRepository.findOneByFirstName(dto.getFirstName());
				if (existingUserOpt.isPresent()) {
					logger.info("UserFirstName already exists.");
					throw new UserAlreadyExistsException();
				}
			}
			user.setFirstName(dto.getFirstName());
			user.setLastName(dto.getLastName());
			user.setEmailId(dto.getEmailId());
			user.setMobileNumber(dto.getMobileNumber());
			user.setUserId(dto.getUserId());
			user.setPassword(encoder.encode(dto.getPassword()));
			List<Role> roles = roleRepository.findByRoleIn(Arrays.asList(dto.getRoles()));
			user.setRoles(roles);

			userTenantRepository.save(user);

			UserDto updatedUserDto = new UserDtoConverter().apply(user);
			return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Void> delete(Long id) {
		Optional<UserTenant> userOpt = userTenantRepository.findById(id);
		if (userOpt.isPresent()) {
			userTenantRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Void> resetPassword(UserDto dto) {
		logger.info("Resetting password for user: " + dto);
		Optional<UserTenant> userOpt = userTenantRepository.findById(dto.getId());
		if (userOpt.isPresent()) {
			UserTenant user = userOpt.get();
			user.setPassword(encoder.encode(dto.getPassword()));
			userTenantRepository.save(user);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Void> deactivateUser(Long id) {
		logger.info("Deactivating user with id: " + id);
		UserTenant user = userTenantRepository.findOneById(id);
		if (user != null) {
			if (user.getStatus().equals(StatusType.Active.toString())) {
				user.setStatus(StatusType.Deactive.toString());
				userTenantRepository.save(user);
				logger.info("User status set to Deactive");
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			} else {
				user.setStatus(StatusType.Active.toString());
				userTenantRepository.save(user);
				logger.info("User status set to Active");
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}
		} else {
			logger.info("User not found with id: " + id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<UserDto>> listUserSimple() {
		List<UserTenant> users = userTenantRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user -> new UserDtoConverter().apply(user))
				.collect(Collectors.toList());
		return new ResponseEntity<>(userDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Role>> listRole() {
		List<Role> roles = roleRepository.findAll();
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
}
