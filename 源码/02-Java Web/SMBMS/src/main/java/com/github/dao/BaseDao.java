package com.github.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Package: com.github.dao
 * @ClassName: BaseDao
 * @Author: subei
 * @CreateTime: 2021/8/5 15:11
 * @Description: 操作数据库的公共类
 */
public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    // 静态代码块,类加载时就初始化了
    static{
        Properties properties = new Properties();
        // 通过类加载器读取对应的资源
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");

        try{
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");

    }

    // 获取数据库的链接
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    // 编写查询公共类
    public static ResultSet execute(Connection connection,PreparedStatement preparedStatement , ResultSet rs, String sql, Object[] params) throws SQLException {
        // 预编译的SQL，在后面直接执行就行了
        preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            // setObject,占位符从1开始，但是数组是从0开始
            preparedStatement.setObject(i+1,params[i]);
        }

        rs = preparedStatement.executeQuery();
        return rs;
    }

    // 编写查询公共类
    public static int execute(Connection connection, PreparedStatement preparedStatement, String sql, Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            // setObject,占位符从1开始，但是数组是从0开始
            preparedStatement.setObject(i+1,params[i]);
        }

        int updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }

    // 释放资源
    public static boolean closeResult(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        boolean flag = true;

        if(resultSet != null){
            try {
                resultSet.close();
                // GC回收
                resultSet = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        if(preparedStatement != null){
            try {
                preparedStatement.close();
                // GC回收
                preparedStatement = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        if(connection != null){
            try {
                connection.close();
                // GC回收
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        return flag;
    }

}
