package com.louis.springboot.demo.service;

import com.louis.springboot.demo.model.Role;
import com.louis.springboot.demo.model.User;

public interface LoginService {

	User addUser(User user);

	Role addRole(Role role);

	User findByName(String name);
	
}