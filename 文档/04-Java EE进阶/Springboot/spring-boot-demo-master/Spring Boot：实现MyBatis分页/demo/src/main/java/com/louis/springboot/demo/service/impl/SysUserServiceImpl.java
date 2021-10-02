package com.louis.springboot.demo.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.louis.springboot.demo.dao.SysUserMapper;
import com.louis.springboot.demo.model.SysUser;
import com.louis.springboot.demo.service.SysUserService;
import com.louis.springboot.demo.util.PageRequest;
import com.louis.springboot.demo.util.PageResult;
import com.louis.springboot.demo.util.PageUtils;

@Service
public class SysUserServiceImpl implements SysUserService {
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Override
    public SysUser findByUserId(Long userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.selectAll();
    }
    
    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }
    
    /**
     * 调用分页插件完成分页
     * @param pageQuery
     * @return
     */
    private PageInfo<SysUser> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> sysMenus = sysUserMapper.selectPage();
        return new PageInfo<SysUser>(sysMenus);
    }
}