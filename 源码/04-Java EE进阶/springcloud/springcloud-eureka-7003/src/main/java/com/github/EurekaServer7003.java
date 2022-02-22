package com.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author subeiLY
 * @create 2022-02-13 20:36
 */
@SpringBootApplication
@EnableEurekaServer // EurekaServer服务端启动类，接受其他微服务注册进来
public class EurekaServer7003 {
    public static void main(String[] args){
        SpringApplication.run(EurekaServer7003.class,args);
    }
}
