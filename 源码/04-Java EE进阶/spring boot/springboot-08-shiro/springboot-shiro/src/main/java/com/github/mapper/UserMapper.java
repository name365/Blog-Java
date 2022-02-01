package com.github.mapper;

import com.github.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author subeiLY
 * @create 2022-02-01 13:05
 */
@Repository
@Mapper
public interface UserMapper {

    public User queryUserByName(String name);

}