package com.chd9.manage.handler;

import com.chd9.manage.exception.NotLoginException;
import com.chd9.manage.utils.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
/**
 * @Author: donnie99
 * @Date: 2020/4/30 23:28
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotLoginException.class)
    @ResponseBody
    private Object notLoginHandler(HttpServletRequest request,Exception e){
        return new Response(-1,null,"请登录","");
    }
}
