package com.github.lesson03;

import com.github.lesson02.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 修改数据
 */
public class TestUpdate {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = Utils.getConnection();
            String sql = "update users set name=?,email=? where id=?";
            st = conn.prepareStatement(sql);
            st.setString(1,"叶凡");
            st.setString(2,"632579682@163.com");
            st.setInt(3,1);
            int num = st.executeUpdate();
            if(num>0){
                System.out.println("更新数据成功！！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.release(conn,st,rs);
        }
    }
}
