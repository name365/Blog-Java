package com.louis.springboot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.springboot.demo.config.dds.DataSource;
import com.louis.springboot.demo.service.SysUserService;

/**
 * 用户控制器
 * @author Louis
 * @date Jun 17, 2019
 */
@RestController
@RequestMapping("user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    
    
    @DataSource(value="master")
    @PostMapping(value="/findAll")
    public Object findAll() {
        return sysUserService.findAll();
    }
    
    @DataSource(value="slave")
    @PostMapping(value="/findAll2")
    public Object findAll2() {
        return sysUserService.findAll();
    }

}