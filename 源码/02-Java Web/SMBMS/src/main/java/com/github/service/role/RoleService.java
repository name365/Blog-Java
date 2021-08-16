package com.github.service.role;

import com.github.pojo.Role;

import java.util.List;

/**
 * @Package: com.github.service.role
 * @ClassName: RoleService
 * @Author: subei
 * @CreateTime: 2021/8/12 17:23
 * @Description:
 */
public interface RoleService {
    // 获取用户角色
    public List<Role> getRoleList();

}
