package com.louis.mango.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class MangoZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangoZuulApplication.class, args);
	}
	
}