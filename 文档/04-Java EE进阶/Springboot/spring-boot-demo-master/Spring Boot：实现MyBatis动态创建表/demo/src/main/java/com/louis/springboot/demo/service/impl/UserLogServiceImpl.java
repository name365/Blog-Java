package com.louis.springboot.demo.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.springboot.demo.dao.UserLogMapper;
import com.louis.springboot.demo.model.UserLog;
import com.louis.springboot.demo.service.UserLogService;

@Service
public class UserLogServiceImpl implements UserLogService {
    
    @Autowired
    private UserLogMapper userLogMapper;
    
    @Override
    public void save(String tableName, UserLog userLog) {
    	// 插入
    	userLogMapper.insertSelective(tableName, userLog);
    }
    
    @Override
    public List<UserLog> findAll(String tableName) {
        return userLogMapper.selectAll(tableName);
    }
}