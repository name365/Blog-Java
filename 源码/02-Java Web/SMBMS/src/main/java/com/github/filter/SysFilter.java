package com.github.filter;

import com.github.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package: com.github.filter
 * @ClassName: SysFilter
 * @Author: subei
 * @CreateTime: 2021/8/6 15:08
 * @Description:
 */
public class SysFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("-------SysFilter--------");
        HttpServletRequest rq = (HttpServletRequest)request;
        HttpServletResponse rp = (HttpServletResponse)response;
        User userSession = (User)rq.getSession().getAttribute("userSession");
        if(userSession==null){
            rp.sendRedirect("/smbms/error.jsp");
        }else{
            chain.doFilter(request, response);
        }

    }

    public void destroy() {

    }
}
