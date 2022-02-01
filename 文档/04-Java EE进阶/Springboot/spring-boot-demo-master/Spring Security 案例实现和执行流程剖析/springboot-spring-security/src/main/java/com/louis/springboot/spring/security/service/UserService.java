package com.louis.springboot.spring.security.service;

import java.util.Set;

import com.louis.springboot.spring.security.model.User;

/**
 * 用户管理
 * @author Louis
 * @date Nov 28, 2018
 */
public interface UserService {

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	User findByUsername(String username);

	/**
	 * 查找用户的菜单权限标识集合
	 * @param userName
	 * @return
	 */
	Set<String> findPermissions(String username);

}
