package com.zzhua.controller;

import com.zzhua.domain.User;
import com.zzhua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/1/30
 * <p>
 * Description:
 */

@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping("toRegister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("register")
    public String regisetr(String username, String password, Model model){
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            model.addAttribute("username",username);
            model.addAttribute("errorMsg","用户名或密码不能为空");
            return "forward:toRegister";
        }
        User oldUser = userService.loginUser(username);
        if (oldUser != null) {
            model.addAttribute("errorMsg","该名称已被注册了，请更换");
            return "forward:toRegister";
        }
        User user = new User(username,password,new Date()); // 注册时间
        userService.addUser(user);
        model.addAttribute("flag",true);
        model.addAttribute("successMsg","注册成功<a href='/login/toLogin'>请登录</a>");
        return "forward:toRegister";
    }

}
