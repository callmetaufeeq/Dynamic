package com.amran.dynamic.multitenant.tenant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.amran.dynamic.multitenant.tenant.entity.User;
import com.amran.dynamic.multitenant.tenant.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userrepo;

	@Override
	public String save(User u) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(u.getPassword());
        u.setPassword(encodedPassword);
		userrepo.save(u);
		return "save success!";
	}

}
