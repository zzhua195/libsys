package com.zzhua.intercepter;

import com.zzhua.domain.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/2/3
 * <p>
 * Description:
 */

public class LoginIntercpter implements HandlerInterceptor {

    private ThreadLocal<Date> localMap = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        localMap.set(new Date());
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        if(principal!=null){
            System.out.println(String.format("时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            System.out.println("请求者: " + principal.getUsername());
            System.out.println("请求的url: " +request.getRequestURL());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("共耗时: " + (new Date().getTime()-localMap.get().getTime()));
        System.out.println("--------------------------------------------------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
