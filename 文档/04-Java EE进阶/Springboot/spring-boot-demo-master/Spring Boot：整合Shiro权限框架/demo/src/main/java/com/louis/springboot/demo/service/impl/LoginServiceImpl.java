package com.louis.springboot.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.springboot.demo.dao.RoleDao;
import com.louis.springboot.demo.dao.UserDao;
import com.louis.springboot.demo.model.Permission;
import com.louis.springboot.demo.model.Role;
import com.louis.springboot.demo.model.User;
import com.louis.springboot.demo.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    //添加用户
    @Override
    public User addUser(User user) {
        userDao.save(user);
        return user;
    }

    //添加角色
    @Override
    public Role addRole(Role role) {
        User user = userDao.findByName(role.getUser().getName());
        role.setUser(user);
        Permission permission1 = new Permission();
        permission1.setPermission("create");
        permission1.setRole(role);
        Permission permission2 = new Permission();
        permission2.setPermission("update");
        permission2.setRole(role);
        List<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermissions(permissions);
        roleDao.save(role);
        return role;
    }

    //查询用户通过用户名
    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }
}