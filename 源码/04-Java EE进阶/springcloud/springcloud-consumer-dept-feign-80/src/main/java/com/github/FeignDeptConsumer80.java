package com.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author subeiLY
 * @create 2022-02-13 11:28
 */
// Ribbon 和 Eureka 整合以后，客户端可以直接调用，不用关心IP地址和端口号
@SpringBootApplication
@EnableEurekaClient
// feign客户端注解,并指定要扫描的包以及配置接口DeptClientService
@EnableFeignClients(basePackages = {"com.github.service"})
// 切记不要加这个注解，不然会出现404访问不到
//@ComponentScan("com.github.service")
public class FeignDeptConsumer80 {
    public static void main(String[] args) {
        SpringApplication.run(FeignDeptConsumer80.class, args);
    }
}


