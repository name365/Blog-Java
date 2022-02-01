package com.louis.springboot.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动器
 * @author Louis
 * @date Nov 28, 2018
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.louis.springboot")
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}
}
