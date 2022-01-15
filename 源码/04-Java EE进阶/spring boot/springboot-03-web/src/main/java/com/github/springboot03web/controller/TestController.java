package com.github.springboot03web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

/**
 * @author subeiLY
 * @create 2022-01-15 21:04
 */
@Controller
public class TestController{
    @RequestMapping("q1")
    public String test(Model model){
        //  存入数据
        //  注：不被转移就是字符串，转义了才是页面
        model.addAttribute("msg","hello,Thymeleaf");
        //  classpath:/templates/test.html
        return "test";
    }

    @RequestMapping("q2")
    public String test2(Map<String,Object> map){
        //  存入数据
        map.put("msg","<h1>QWE</h1>");
        map.put("users", Arrays.asList("杨丙寅","github"));
        return "test";
    }
}