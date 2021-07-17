package com.github.lesson02;

import com.github.lesson02.utils.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author subeiLY
 * @create 2021-07-17 10:50
 */
public class SQL注入 {
    public static void main(String[] args) {
        login("lisi","123456");    // 正常登录
//        login("'or '1=1","123456");  // SQL注入
    }

    // 登陆业务
    public static void login(String username,String password){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = Utils.getConnection();
            st = conn.createStatement();
            // select * from users where name=''or '1-1' and password='123456'
            String sql = "select * from users where name='"+username
                    +"' and password='"+password+"'";
            rs = st.executeQuery(sql);
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
