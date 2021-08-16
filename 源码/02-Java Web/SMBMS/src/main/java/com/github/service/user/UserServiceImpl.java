package com.github.service.user;

import com.github.dao.BaseDao;
import com.github.dao.user.UserDao;
import com.github.dao.user.UserDaoImpl;
import com.github.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Package: com.github.service.user
 * @ClassName: UserServiceImpl
 * @Author: subei
 * @CreateTime: 2021/8/5 20:41
 * @Description:
 */
public class UserServiceImpl implements UserService{
    // 业务层都会调用dao层，所以需要引入Dao层
    private UserDao userDao;
    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    // 用户登录
    @Override
    public User login(String userCode, String userPassword) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getConnection();
            // 通过业务层调用对应的具体的数据库操作
            user = userDao.getLoginUser(connection,userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResult(connection,null,null);
        }

        // 匹配密码
        if(null != user) {
            if (!user.getUserPassword().equals(userPassword)) {
                user = null;
            }
        }

        return user;
    }

    // 根据user的ID修改密码
    @Override
    public boolean updatePwd(int id, String pwd) {
        System.out.println("UserServlet2:" + pwd);

        boolean flag = false;
        Connection connection = null;
        try{
            connection = BaseDao.getConnection();
            if(userDao.updatePwd(connection,id,pwd) > 0) {
                flag = true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseDao.closeResult(connection, null, null);
        }
        return flag;
    }

    // 根据条件查询出用户表记录数
    @Override
    public int getUserCount(String queryUserName, int queryUserRole) {
        int count = 0;
        Connection connection = null;

        System.out.println("queryUserName----->" + queryUserName);
        System.out.println("queryUserRole----->" + queryUserRole);

        try {
            connection = BaseDao.getConnection();
            count = userDao.getUserCount(connection,queryUserName,queryUserRole);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResult(connection,null,null);
        }

        return count;
    }

    // 根据条件查询用户列表
    @Override
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList = null;
        System.out.println("queryUserName ---- > " + queryUserName);
        System.out.println("queryUserRole ---- > " + queryUserRole);
        System.out.println("currentPageNo ---- > " + currentPageNo);
        System.out.println("pageSize ---- > " + pageSize);
        try {
            connection = BaseDao.getConnection();
            userList = userDao.getUserList(connection, queryUserName,queryUserRole,currentPageNo,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseDao.closeResult(connection, null, null);
        }
        return userList;
    }

    // 增加用户信息
    @Override
    public boolean add(User user) {
        boolean flag = false;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            // 开启JDBC事务管理
            connection.setAutoCommit(false);
            int updateRows = userDao.add(connection,user);
            connection.commit();
            if(updateRows > 0){
                flag = true;
                System.out.println("add success!");
            }else{
                System.out.println("add failed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            // 在service层进行connection连接的关闭
            BaseDao.closeResult(connection, null, null);
        }
        return flag;
    }

    // 通过userId删除user
    @Override
    public boolean deleteUserById(Integer delId) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if(userDao.deleteUserById(connection,delId) > 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseDao.closeResult(connection, null, null);
        }
        return flag;
    }

    // 修改用户信息
    @Override
    public boolean modify(User user) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if(userDao.modify(connection,user) > 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseDao.closeResult(connection, null, null);
        }
        return flag;
    }

    // 通过userId查询user
    @Override
    public User getUserById(String id) {
        User user = null;
        Connection connection = null;
        try{
            connection = BaseDao.getConnection();
            user = userDao.getUserById(connection,id);
        }catch (Exception e) {
            e.printStackTrace();
            user = null;
        }finally{
            BaseDao.closeResult(connection, null, null);
        }
        return user;
    }

    // 根据userCode查询出User
    @Override
    public User selectUserCodeExist(String userCode) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            user = userDao.getLoginUser(connection, userCode);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseDao.closeResult(connection, null, null);
        }
        return user;
    }

}


















