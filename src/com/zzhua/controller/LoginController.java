package com.zzhua.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/1/28
 * <p>
 * Description:
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model, HttpSession session){
        model.addAttribute("username",username);
        model.addAttribute("password",password);
        Subject subject = SecurityUtils.getSubject();
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            model.addAttribute("errorMsg","用户名或密码不能为空");
            return "forward:/login/toLogin";
        }else{
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                subject.login(token);
                session.setAttribute("user",username);
                return "redirect:/book/list";
            } catch (UnknownAccountException ex) {
                model.addAttribute("errorMsg","该用户名不存在");
            } catch (IncorrectCredentialsException ex){
                model.addAttribute("errorMsg","密码错误");
            } catch (AuthenticationException ex){
                model.addAttribute("errorMsg","此账户暂时无法登陆，请联系管理员");
            }
            return "forward:/login/toLogin";
        }
    }
}
