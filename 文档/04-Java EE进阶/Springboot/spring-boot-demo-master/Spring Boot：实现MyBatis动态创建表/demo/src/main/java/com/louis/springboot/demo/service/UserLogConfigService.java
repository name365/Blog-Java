package com.louis.springboot.demo.service;
import java.util.List;

import com.louis.springboot.demo.model.UserLogConfig;

public interface UserLogConfigService {

    /**
     * 保存用户日志配置
     * @return
     */
    void save(UserLogConfig userLogConfig);
    
    /**
     * 查找全部用户日志配置
     * @return
     */
    List<UserLogConfig> findAll();

}