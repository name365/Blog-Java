package com.github.springboot01helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  // @SpringBootApplication 标注一个主程序类， 说明这是一个Spring Boot应用
 */
@SpringBootApplication
public class Springboot01HelloworldApplication {

    public static void main(String[] args) {
        // 启动一个服务
        SpringApplication.run(Springboot01HelloworldApplication.class, args);
    }

}
