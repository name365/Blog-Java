package com.github.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author subeiLY
 * @create 2021-07-19 11:04
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 响应的类型：HTML
        PrintWriter out = resp.getWriter();
        // 获取响应的输出
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World!<title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>你好</h1>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
