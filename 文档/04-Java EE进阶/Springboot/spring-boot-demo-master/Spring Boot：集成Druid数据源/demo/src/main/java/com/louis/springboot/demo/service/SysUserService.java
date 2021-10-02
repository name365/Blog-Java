package com.louis.springboot.demo.service;
import java.util.List;

import com.louis.springboot.demo.model.SysUser;

public interface SysUserService {

    /**
     * 根据用户ID查找用户
     * @param userId
     * @return
     */
    SysUser findByUserId(Long userId);

    /**
     * 查找所有用户
     * @return
     */
    List<SysUser> findAll();

}