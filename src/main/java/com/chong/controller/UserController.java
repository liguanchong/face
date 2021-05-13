package com.chong.controller;

import com.chong.domain.User;
import com.chong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/save")
    public String save(User user)  {
        User username = userService.findByName(user.getName());
        // 如果数据库中没有该用户，可以注册，否则跳转页面
        if (username == null) {
            // 添加用户
            userService.saveUser(user);

            return "redirect:register_ok.html";
        }else {
            // 注册失败跳转到错误页面
            return "redirect:register_no.html";
        }
    }

    @RequestMapping("/login")
    public String login(User user, HttpSession session) {
        //数据库查询登录
        User loginUser = userService.login(user);

        //判断loginUser是否为空
        if (loginUser != null) {
            //登录成功跳转到欢迎页
            session.setAttribute("loginUser", user);
            return "redirect:/loginok.html";
        } else {
            //为空，登录失败，继续登录
            return "redirect:/loginno.html";
        }
    }
}