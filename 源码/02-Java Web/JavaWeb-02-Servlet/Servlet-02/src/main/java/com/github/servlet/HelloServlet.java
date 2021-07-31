package com.github.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author subeiLY
 * @create 2021-07-21 10:50
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.getInitParameter(); 初始化参数
//        this.getServletConfig(); Servlet配置
//        this.getServletContext(); Servlet上下文
        ServletContext context = this.getServletContext();

        String name = "学习超好";   // 数据
        // 将一个数据保存在了ServletContext中，名字为：name ,值 name
        context.setAttribute("name",name);

        System.out.println("Hello");

    }
}
