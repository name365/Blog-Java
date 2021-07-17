package com.github.lesson03;

import com.github.lesson02.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 删除数据
 */
public class TestDelete {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = Utils.getConnection();
            String sql = "delete * from users where id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1,4);
            int num = st.executeUpdate(sql);
            if(num>0){
                System.out.println("删除数据成功！！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.release(conn,st,rs);
        }
    }
}
