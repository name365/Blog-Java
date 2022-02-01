package com.louis.springboot.demo.service;

import java.util.Set;

import com.louis.springboot.demo.model.User;

/**
 * 用户管理
 * @author Louis
 * @date Jun 29, 2019
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
