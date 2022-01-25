package com.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Springboot04WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot04WebApplication.class, args);
    }

}
