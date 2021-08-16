package com.github.service.role;

import com.github.dao.BaseDao;
import com.github.dao.role.RoleDao;
import com.github.dao.role.RoleDaoImpl;
import com.github.pojo.Role;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @Package: com.github.service.role
 * @ClassName: RoleServiceImpl
 * @Author: subei
 * @CreateTime: 2021/8/12 17:24
 * @Description:
 */
public class RoleServiceImpl implements RoleService{
    // 引入Dao
    private RoleDao roleDao;

    public RoleServiceImpl(){
        roleDao = new RoleDaoImpl();
    }

    @Override
    public List<Role> getRoleList() {
        Connection connection = null;
        List<Role> roleList = null;
        try {
            connection = BaseDao.getConnection();
            roleList = roleDao.getRoleList(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseDao.closeResult(connection, null, null);
        }
        return roleList;
    }

    @Test
    public void test(){
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        for(Role role:roleList){
            System.out.println(role.getRoleName());
        }

    }
}
