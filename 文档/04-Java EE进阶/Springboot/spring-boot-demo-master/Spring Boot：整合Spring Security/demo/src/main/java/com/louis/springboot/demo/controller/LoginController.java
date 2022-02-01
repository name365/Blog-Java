package com.louis.springboot.demo.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.louis.springboot.demo.security.JwtAuthenticatioToken;
import com.louis.springboot.demo.utils.SecurityUtils;
import com.louis.springboot.demo.vo.HttpResult;
import com.louis.springboot.demo.vo.LoginBean;

/**
 * 登录控制器
 * @author Louis
 * @date Jun 29, 2019
 */
@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 登录接口
     */
    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        
        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);
                
        return HttpResult.ok(token);
    }

}