package com.amran.dynamic.multitenant.mastertenant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amran.dynamic.multitenant.mastertenant.userUtils.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	List<Role> findByRoleIn(List<String> roles);

	public Optional<Role> findByRole(String role);

}
