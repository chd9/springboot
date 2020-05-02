package com.chd9.manage.interceptor;

import com.chd9.manage.exception.NotLoginException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @Author: donnie99
 * @Date: 2020/4/30 23:28
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Object obj = request.getSession().getAttribute("admin_user");
        if(obj != null) return true;
        String requestWith = request.getHeader("x-requested-with");
        if(requestWith != null && requestWith.equalsIgnoreCase("XMLHttpRequest")){
            //ajax请求
            throw new NotLoginException();
        }else{
            response.sendRedirect("/auth/login");
        }
        return false;
    }
}
