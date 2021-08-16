package com.github.test;

import java.sql.*;

/**
 * @Package: com.github.test
 * @ClassName: TestJDBC2
 * @Author: subei
 * @CreateTime: 2021/8/4 10:35
 * @Description:
 */
public class TestJDBC2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 配置信息
        // useUnicode=true&characterEncoding=utf-8 解决中文乱码
        String url="jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "root";

        // 1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2.连接数据库,代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3.编写SQL
        String sql = "insert into  users(id, name, password, email, birthday) values (?,?,?,?,?);";

        // 4.预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 给第一个占位符？ 的值赋值为1；
        preparedStatement.setInt(1,2);
        // 给第二个占位符？ 的值赋值为狂神说Java；
        preparedStatement.setString(2,"哇哈哈AD钙");
        // 给第三个占位符？ 的值赋值为123456；
        preparedStatement.setString(3,"123456");
        // 给第四个占位符？ 的值赋值为1；
        preparedStatement.setString(4,"24736743@qq.com");
        // 给第五个占位符？ 的值赋值为new Date(new java.util.Date().getTime())；
        preparedStatement.setDate(5,new Date(new java.util.Date().getTime()));

        // 5.执行SQL
        int i = preparedStatement.executeUpdate();

        if (i>0){
            System.out.println("插入成功@");
        }

        // 6.关闭连接，释放资源（一定要做） 先开后关
        preparedStatement.close();
        connection.close();
    }
}
