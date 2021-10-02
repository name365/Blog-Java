package com.louis.springboot.demo.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.springboot.demo.dao.SysUserMapper;
import com.louis.springboot.demo.model.SysUser;
import com.louis.springboot.demo.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.selectAll();
    }
}