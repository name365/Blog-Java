package com.github.lesson03;

import com.github.lesson02.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author subeiLY
 * @create 2021-07-17 10:50
 */
public class SQL注入 {
    public static void main(String[] args) {
        login("lisi","123456");    // 正常登录  ==结果==>结果输出
//        login("'or '1=1","123456");  // SQL注入  ==结果==>无结果输出
    }

    // 登陆业务
    public static void login(String username,String password){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = Utils.getConnection();
            String sql = "select * from users where name=? and password=?";
            st = conn.prepareStatement(sql);
            st.setString(1,username);
            st.setString(2,password);
            rs = st.executeQuery();
            while(rs.next()){
                System.out.println("查询数据成功！！！");
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("password"));
                System.out.println("==========");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utils.release(conn,st,rs);
        }
    }
}
