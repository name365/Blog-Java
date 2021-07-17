package com.github.lesson01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 第一个JDBC程序
 * 
 * @author subeiLY
 * @create 2021-07-16 19:32
 */
public class JdbcFirstDemo {
    public static void main(String[] args) throws Exception{
        // 1.加载驱动
        Class.forName("com.mysql.jdbc.Driver"); // 固定写法，加载驱动

        // 2.用户信息和url
        // uesUnicode=true 支持中文编码
        // characterEncoding=utf8 设定字符集
        // useSSL=true 使用安全的连接
        String url = "jdbc:mysql://localhost:3306/jdbcStudy?uesUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "root";

        // 3.连接成功，数据库对象 connection 代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        // 4.执行SQL对象
        Statement statement = connection.createStatement();

        // 5.执行SQL对象，执行SQL
        String sql = "SELECT * FROM users";

        ResultSet resultSet = statement.executeQuery(sql);  // 返回结果集，结果集中封装了我们全部查询出来的对象

        while(resultSet.next()){
            System.out.println("id=" + resultSet.getObject("id"));
            System.out.println("name=" + resultSet.getObject("NAME"));
            System.out.println("pwd=" + resultSet.getObject("PASSWORD"));
            System.out.println("email=" + resultSet.getObject("email"));
            System.out.println("both=" + resultSet.getObject("birthday"));
        }

        // 6.释放连接
        resultSet.close();
        statement.close();
        connection.close();

    }
}
