package com.louis.springboot.demo.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.springboot.demo.dao.UserLogConfigMapper;
import com.louis.springboot.demo.dao.UserLogMapper;
import com.louis.springboot.demo.model.UserLogConfig;
import com.louis.springboot.demo.service.UserLogConfigService;

@Service
public class UserLogConfigServiceImpl implements UserLogConfigService {
    
    @Autowired
    private UserLogConfigMapper userLogConfigMapper;
    @Autowired
    private UserLogMapper userLogMapper;
    
    @Override
    public void save(UserLogConfig userLogConfig) {
    	// 插入
    	userLogConfigMapper.insertSelective(userLogConfig);
    	// 添加配置时，创建日志存储表
    	String tableName = userLogConfig.getTableName();
    	if(userLogMapper.existTable(tableName) > 0) {
    		userLogMapper.dropTable(tableName);
    	}
    	userLogMapper.createTable(tableName);
    }
    
    @Override
    public List<UserLogConfig> findAll() {
        return userLogConfigMapper.selectAll();
    }
}