package com.louis.springboot.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.louis.springboot.demo.model.SysUser;
import com.louis.springboot.demo.service.SysUserService;

@RestController
@RequestMapping("user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    
    @PostMapping(value="/save")
    public Object save(@RequestBody SysUser user) {
    	sysUserService.save(user);
        return 1;
    }
    
    @GetMapping(value="/delete")
    public Object delete(@RequestParam("id") String id) {
    	sysUserService.delete(id);
    	return 1;
    }
    
    @GetMapping(value="/findAll")
    public Object findAll() {
        return sysUserService.findAll();
    }
    
}