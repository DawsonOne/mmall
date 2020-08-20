package com.gosang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gosang.Exception.MMallException;
import com.gosang.entity.User;
import com.gosang.enums.ExceptionEnum;
import com.gosang.enums.GenderEnum;
import com.gosang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Wrapper;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gosang
 * @since 2020-08-17
 */
@Controller
@RequestMapping("//user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(User user){
        boolean save = userService.register(user);
        if (!save){
            throw new MMallException(ExceptionEnum.REGISTER_FAIL);
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(User user, HttpSession session){
        User user1 = this.userService.login(user);
        if (user1 != null){
            session.setAttribute("user",user1);
            return "main";
        } else {
          return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("list",this.userService.list());
        return modelAndView;

    }
}

