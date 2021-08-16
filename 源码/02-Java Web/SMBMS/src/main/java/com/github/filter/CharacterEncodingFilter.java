package com.github.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Package: com.github.filter
 * @ClassName: CharacterEncodingFilter
 * @Author: subei
 * @CreateTime: 2021/8/5 16:26
 * @Description:
 */
public class CharacterEncodingFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        chain.doFilter(request,response);
    }

    public void destroy() {

    }
}
