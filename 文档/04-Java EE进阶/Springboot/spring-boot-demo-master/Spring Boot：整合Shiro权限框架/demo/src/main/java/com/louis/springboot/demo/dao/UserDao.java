package com.louis.springboot.demo.dao;

import com.louis.springboot.demo.model.User;

public interface UserDao extends BaseDao<User, Long> {
	
	User findByName(String name);
	
}