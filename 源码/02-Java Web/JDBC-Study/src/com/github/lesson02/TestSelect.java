package com.github.lesson02;

import com.github.lesson02.utils.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 查询数据
 */
public class TestSelect {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = Utils.getConnection();
            st = conn.createStatement();
//            String sql = "select * from users";
            String sql = "select * from users where id=3";
            rs = st.executeQuery(sql);
            while(rs.next()){
                System.out.println("查询数据成功！！！");
//                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.release(conn,st,rs);
        }
    }
}
