package com.louis.mango.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * 启动器
 * @author Louis
 * @date Jan 15, 2019
 */
@EnableAdminServer
@SpringBootApplication
public class MangoMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoMonitorApplication.class, args);
	}
}