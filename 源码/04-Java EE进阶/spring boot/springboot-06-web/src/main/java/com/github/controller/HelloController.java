package com.github.controller;

import com.github.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @author subeiLY
 * @create 2022-01-27 20:34
 */
@RestController
public class HelloController {
    //  默认请求  -->  /error
    @RequestMapping(value = "/hello")
    public String hello(){
        return "Hello";
    }

    @RequestMapping("/getUser")
    public User getUser(){
        return new User();
    }

    // Operation接口，不是放在类上的，是方法
    @ApiOperation("Github控制类")
    @GetMapping(value = "/github")
    @ResponseBody
    public String github(@ApiParam("用户名") String username){
        return "Hay" + username;
    }

    @ApiOperation("Post测试类")
    @PostMapping(value = "/postt")
    @ResponseBody
    public User postt(@ApiParam("用户名") User user){
        return user;
    }

}
