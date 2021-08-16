package com.github.servlet.user;

import com.github.pojo.User;
import com.github.service.user.UserServiceImpl;
import com.github.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package: com.github.servlet.user
 * @ClassName: LoginServlet
 * @Author: subei
 * @CreateTime: 2021/8/6 10:31
 * @Description:
 */
public class LoginServlet extends HttpServlet {
    // servlet:控制层，调用 业务层代码
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("--------LoginServlet----------");
        // 获取用户名和密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        // 调用业务层，进行用户密码匹配
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.login(userCode, userPassword);

        if(user != null){   // 登录成功
            // 将用户信息放入session
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            // 页面跳转 —— frame.jsp
            resp.sendRedirect("jsp/frame.jsp");
        } else {
            // 页面跳转 —— login.jsp
            req.setAttribute("error","用户名或密码错误！");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
