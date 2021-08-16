package com.github.dao.role;

import com.github.dao.BaseDao;
import com.github.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.github.dao.role
 * @ClassName: RoleDaoImpl
 * @Author: subei
 * @CreateTime: 2021/8/12 17:22
 * @Description:
 */
public class RoleDaoImpl implements RoleDao{

    // 获取角色列表
    public List<Role> getRoleList(Connection connection) throws SQLException {

        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        ArrayList<Role> roleList = new ArrayList<Role>();

        if(connection!=null){
            String sql = "select * from smbms_role";
            Object[] params = {};
            resultSet = BaseDao.execute(connection,pstm,resultSet,sql,params);

            while(resultSet.next()){
                Role _role = new Role();
                _role.setId(resultSet.getInt("id"));
                _role.setRoleName(resultSet.getString("roleName"));
                _role.setRoleCode(resultSet.getString("roleCode"));
                roleList.add(_role);
            }
            BaseDao.closeResult(null,pstm,resultSet);
        }

        return roleList;
    }
}
