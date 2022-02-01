package com.louis.springboot.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.springboot.demo.model.UserLog;
import com.louis.springboot.demo.service.UserLogService;

@RestController
@RequestMapping("user/log")
public class UserLogController {

    @Autowired
    private UserLogService userLogService;
    
    @PostMapping(value="/save")
    public Object save(@RequestBody UserLog userLog) {
    	String tableName = userLog.getTableName();
    	userLogService.save(tableName, userLog);
    	return 1;
    }
    
    @GetMapping(value="/findAll")
    public Object findAll(String tableName) {
        return userLogService.findAll(tableName);
    }
}