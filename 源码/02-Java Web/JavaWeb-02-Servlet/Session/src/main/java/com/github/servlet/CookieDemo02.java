package com.github.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author subeiLY
 * @create 2021-07-31 9:43
 */
public class CookieDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        // 新建一个cookie,名字必须要一致
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis() + "");
        // 设置cookie的有效期
        cookie.setMaxAge(24*60*60);
        // 响应给客户端一个cookie
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
