package com.github.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author subeiLY
 * @create 2022-03-03 21:16
 */
@Repository // 持久层
public interface UserMapper extends BaseMapper<User> {
    // 无需添加CRUD操作
}