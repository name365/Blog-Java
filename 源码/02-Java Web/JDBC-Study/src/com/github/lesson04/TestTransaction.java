package com.github.lesson04;

import com.github.lesson02.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 模拟转账失败时的业务场景
 * @author subeiLY
 * @create 2021-07-17 18:17
 */
public class TestTransaction {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            // 关闭数据库的自动提交，自动开启事务
            conn = Utils.getConnection();
            // 开启事务
            conn.setAutoCommit(false);

            String sql1 = "update account set money=money-100 where name='A'";
            st = conn.prepareStatement(sql1);
            st.executeUpdate();

//            int x=1/0;  // 报错

            String sql2 = "update account set money=money+100 where name='B'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();

            // 业务完毕，提交事务
            conn.commit();
            System.out.println("转账成功！！！");
        } catch (Exception e) {
            try {
                conn.rollback(); // 失败，则事务回滚
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            Utils.release(conn,st,rs);
        }

    }
}
