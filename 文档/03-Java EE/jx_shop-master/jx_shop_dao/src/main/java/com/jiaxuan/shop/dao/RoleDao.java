package com.jiaxuan.shop.dao;

import com.jiaxuan.shop.pojo.Role;

import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-11 11:29
 * Description: 角色接口层
 */
public interface RoleDao {
    List<Role> selectAllRoles();
}
