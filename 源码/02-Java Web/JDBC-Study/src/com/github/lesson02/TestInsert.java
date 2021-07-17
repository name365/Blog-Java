package com.github.lesson02;

import com.github.lesson02.utils.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 插入数据
 */
public class TestInsert {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            // 获取一个数据库连接
            conn = Utils.getConnection();

            // 通过conn对象获取负责执行SQL命令的Statement对象
            st = conn.createStatement();

            // 要执行的SQL
            String sql = "insert into users(id,name,password,email,birthday) " +
                    "values(4,'subei','123','2473652743@qq.com','2021-07-01')";

            // 执行操作
            int num = st.executeUpdate(sql);
            if(num>0){
                System.out.println("插入数据成功！！！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            Utils.release(conn,st,rs);
        }
    }
}
