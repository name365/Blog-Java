package com.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动类
 * @author subeiLY
 * @create 2022-02-13 10:30
 */
@SpringBootApplication
@EnableEurekaClient // 本服务启动之后会自动注册进Eureka中！
@EnableDiscoveryClient // 开启服务发现客户端的注解，可以用来获取一些配置的信息，得到具体的微服务

public class DeptProvider8003 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8003.class,args);
    }
}
