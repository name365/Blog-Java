package com.github.lesson03;

import com.github.lesson02.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author subeiLY
 * @create 2021-07-17 16:07
 */
public class TestInsert {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            String sql = "insert into users(id,name,password,email,birthday) values (?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            // 为SQL参数赋值，索引是从1开始的
            st.setInt(1,5); // id是int类型
            st.setString(2,"subei"); // name是字符串类型
            st.setString(3,"123456"); // password是字符串类型
            st.setString(4,"24635862@qq.com"); // email是字符串类型
            st.setDate(5,new java.sql.Date(System.currentTimeMillis())); // birthday是date类型

            /*
            * 这里有个小问题:
            *   在使用  new Date().getTime() 时，会报错：请使用System.currentTimeMillis()代替new Date().getTime()
            * 对于这个问题，百度一下：
            *   new Date()所做的事情其实就是调用了System.currentTimeMillis()。
            *   如果仅仅是需要或者毫秒数，那么完全可以使用System.currentTimeMillis()去代替new Date()，
            *   效率上会高一点。况且很多人喜欢在同一个方法里面多次使用new Date()，
            *   通常性能就是这样一点一点地消耗掉，这里其实可以声明一个引用。
             * */

            // 执行插入数据操作
            int num = st.executeUpdate();
            if(num>0){
                System.out.println("插入数据成功！！！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // SQL释放资源
            Utils.release(conn,st,rs);
        }

    }
}
