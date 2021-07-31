package com.github.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 中文数据传递
 * @author subeiLY
 * @create 2021-07-31 9:48
 */
public class CookieDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决中文乱码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        Cookie[] cookies = req.getCookies();

        // 判断Cookie是否存在
        if(cookies!=null){
            // 如果存在，遍历数组
            writer.write("你上一次访问的用户是:");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                // 获取Cookie的名字
                if(cookie.getName().equals("name")) {
                    // 解码
                    writer.write(URLDecoder.decode(cookie.getValue(),"UTF-8"));
                }
            }
        }else{
            writer.write("第一次访问本站！！！");
        }

        // 编码
        Cookie cookie = new Cookie("name", URLEncoder.encode("哇哈哈","UTF-8"));
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
