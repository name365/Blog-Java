package com.github.dao.role;

import com.github.pojo.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Package: com.github.dao.role
 * @ClassName: RoleDao
 * @Author: subei
 * @CreateTime: 2021/8/12 17:21
 * @Description:
 */
public interface RoleDao {
    // 获取角色列表
    public List<Role> getRoleList(Connection connection) throws SQLException;

}
