package com.github.servlet;

import com.github.pojo.User;
import com.github.util.Sendmail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @Package: com.github.servlet
 * @ClassName: RegisterServlet
 * @Author: subei
 * @CreateTime: 2021/8/16 20:31
 * @Description:
 */

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.接收用户填写的表单数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        System.out.println(username+password+email);

        // 2.向用户邮箱发送邮件，注意发送邮件很耗时，所以我们启动一个子线程去做这件事，而用户则是直接跳转注册成功页面，以免降低用户体验
        User user = new User(username,password,email);
        // 获取子线程对象
        Sendmail sendmail = new Sendmail(user);
        // 启动子线程
        new Thread(sendmail).start();

        // 3.视图跳转
        req.setAttribute("message","注册成功！我们已经向您的邮箱发送了邮件，请您及时进行查收。由于网络原因，您收到邮件的时间存在延迟，敬请谅解~");
        req.getRequestDispatcher("info.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
