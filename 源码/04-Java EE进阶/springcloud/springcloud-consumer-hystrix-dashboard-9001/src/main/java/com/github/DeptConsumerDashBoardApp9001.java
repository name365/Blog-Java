package com.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author subeiLY
 * @create 2022-02-24 19:59
 */
@SpringBootApplication
@EnableHystrixDashboard
public class DeptConsumerDashBoardApp9001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerDashBoardApp9001.class,args);
    }
}

