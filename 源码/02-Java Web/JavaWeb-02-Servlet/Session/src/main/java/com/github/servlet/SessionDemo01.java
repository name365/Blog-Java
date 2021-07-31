package com.github.servlet;

import com.github.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author subeiLY
 * @create 2021-07-28 8:39
 */
public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码问题
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        // 得到Session
        HttpSession session = req.getSession();
        // 在session中存东西
        session.setAttribute("name",new Person("懒羊羊",1));
        // 获取Session中的ID
        String id = session.getId();

        // 判断Session是否为新创建的
        if(session.isNew()){
            resp.getWriter().write("session创建成功！ID:" + id);
        }else{
            resp.getWriter().write("session已经在服务器中存在了！ID:" + id);
        }

        // Session创建的时候做了什么事情:
//        Cookie cookie = new Cookie("JSESSIONID", id);
//        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

//// 得到Session
//HttpSession session = req.getSession();
//
//Person person = (Person) session.getAttribute("name");
//
//System.out.println(person.toString());
//
//HttpSession session = req.getSession();
//session.removeAttribute("name");
//// 手动注销Session
//session.invalidate();