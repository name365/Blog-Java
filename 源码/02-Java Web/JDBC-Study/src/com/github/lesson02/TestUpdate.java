package com.github.lesson02;

import com.github.lesson02.utils.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 修改数据
 */
public class TestUpdate {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = Utils.getConnection();
            st = conn.createStatement();
            String sql = "update users set name='王伟',email='wangwei@163.com' where id=3";
            int num = st.executeUpdate(sql);
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
