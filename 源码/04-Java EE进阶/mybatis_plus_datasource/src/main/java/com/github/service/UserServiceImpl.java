package com.github.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.mapper.UserMapper;
import com.github.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author subeiLY
 * @create 2022-03-04 21:47
 */
@DS("master") //指定所操作的数据源
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
