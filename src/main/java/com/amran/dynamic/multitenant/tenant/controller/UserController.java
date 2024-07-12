package com.amran.dynamic.multitenant.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amran.dynamic.multitenant.tenant.entity.User;
import com.amran.dynamic.multitenant.tenant.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userv;
	
	@PostMapping("/save")
	public String saveUser(@RequestBody User u) {
		return userv.save(u);
	}

}
