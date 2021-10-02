package com.jiaxuan.shop.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName BackSessionInteceptor
 * @Description TODO
 * @Author jx
 * @Date 2019/04/07 11:22
 * @Version 1.0
 **/
public class BackSessionInteceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //判断session是否有用户信息
        if (request.getSession().getAttribute("systemUser") != null) {
            //存在则放行
            return true;
        }
        request.getRequestDispatcher("/admin/system_user/manager/sessionTimeOut").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
