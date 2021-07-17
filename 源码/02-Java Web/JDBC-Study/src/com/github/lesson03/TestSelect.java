package com.github.lesson03;

import com.github.lesson02.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 查询数据
 */
public class TestSelect {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = Utils.getConnection();
            String sql = "select * from users where id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1,1);
            rs = st.executeQuery();
            if(rs.next()){
                System.out.println(rs.getString("name"));
                System.out.println("查询数据成功！！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.release(conn,st,rs);
        }
    }
}
