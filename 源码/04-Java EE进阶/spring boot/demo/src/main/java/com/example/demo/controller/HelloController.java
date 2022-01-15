package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author subeiLY
 * @create 2022-01-10 12:30
 */

/**
 *  自动装配
 */
@RestController
public class HelloController {
    /**
     *  接口：
     */
    @RequestMapping("/hello")
    public String hello(){
        // 调用业务，接收前端参数
        return "Hello,SpringBoot!";
    }
}
