package com.atguigu.studentmanager.interceptor;

import com.atguigu.studentmanager.domain.Admin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SystemInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if(user==null){
            //跳转到登录页面
            response.sendRedirect(request.getContextPath());
            return false;
        }
        return true;
    }
}
