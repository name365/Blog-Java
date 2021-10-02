package com.jiaxuan.shop.service.impl;

import com.jiaxuan.shop.dao.RoleDao;
import com.jiaxuan.shop.pojo.Role;
import com.jiaxuan.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-11 11:33
 * Description:角色业务层实现
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public List<Role> findAllRoles() {
        return roleDao.selectAllRoles();
    }
}
