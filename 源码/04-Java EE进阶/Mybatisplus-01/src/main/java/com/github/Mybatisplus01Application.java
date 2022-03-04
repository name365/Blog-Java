package com.github;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.mapper")
public class Mybatisplus01Application {

    public static void main(String[] args) {
        SpringApplication.run(Mybatisplus01Application.class, args);
    }

}
