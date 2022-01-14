package com.github.springboot01helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author subeiLY
 * @create 2022-01-12 12:02
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/subei")
    @ResponseBody
    public String hello(){
        return "hello";
    }

}
