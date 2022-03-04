package com.github.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author subeiLY
 * @create 2022-03-04 21:40
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
