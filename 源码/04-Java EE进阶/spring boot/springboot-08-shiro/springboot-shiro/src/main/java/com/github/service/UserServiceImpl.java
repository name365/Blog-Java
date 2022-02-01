package com.github.service;

import com.github.mapper.UserMapper;
import com.github.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author subeiLY
 * @create 2022-02-01 13:07
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper mapper;


    @Override
    public User queryUserByName(String name) {
        return mapper.queryUserByName(name);
    }
}


