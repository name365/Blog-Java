package com.github.lesson05;

import com.github.lesson05.utils.Utils_C3P0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author subeiLY
 * @create 2021-07-17 20:44
 */
public class TestC3P0 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = Utils_C3P0.getConnection();
            String sql = "insert into users(id,name,password,email,birthday) values (?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            st.setInt(1,7);
            st.setString(2,"pink");
            st.setString(3,"263223");
            st.setString(4,"3276128203@qq.com");
            st.setDate(5,new java.sql.Date(System.currentTimeMillis()));
            // 执行插入数据操作
            int num = st.executeUpdate();
            if(num>0){
                System.out.println("插入数据成功！！！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // SQL释放资源
            Utils_C3P0.release(conn,st,rs);
        }
    }
}
