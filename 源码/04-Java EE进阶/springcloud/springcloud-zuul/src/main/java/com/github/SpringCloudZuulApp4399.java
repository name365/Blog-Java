package com.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author subeiLY
 * @create 2022-02-24 21:15
 */
@SpringBootApplication
@EnableZuulProxy // 开启Zuul
public class SpringCloudZuulApp4399 {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudZuulApp4399.class,args);
    }
}
