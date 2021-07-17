package com.github.lesson02.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author subeiLY
 * @create 2021-07-17 8:59
 */
public class Utils {

    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static{
        try{
            // 读取db.properties文件中的数据库连接信息
            InputStream in = Utils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);

            // 获取数据库连接驱动
            driver = properties.getProperty("driver");
            // 获取数据库连接URL地址
            url = properties.getProperty("url");
            // 获取数据库连接用户名
            username = properties.getProperty("username");
            // 获取数据库连接密码
            password = properties.getProperty("password");

            // 加载数据库驱动，只需加载一次！
            Class.forName(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取连接对象
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }

    // 释放资源
    public static void release(Connection conn, Statement st, ResultSet rs){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(st!=null){
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

