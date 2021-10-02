package com.louis.springboot.spring.security.vo;

/**
 * 登录接口封装对象
 * @author Louis
 * @date Nov 28, 2018
 */
public class LoginBean {

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
