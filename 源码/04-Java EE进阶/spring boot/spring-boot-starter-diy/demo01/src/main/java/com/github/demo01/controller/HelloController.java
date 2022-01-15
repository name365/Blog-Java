package com.github.demo01.controller;

import com.github.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author subeiLY
 * @create 2022-01-15 14:55
 */
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @RequestMapping("/qwe")
    public String hello(){
        return helloService.sayHello("whh");
    }
}
