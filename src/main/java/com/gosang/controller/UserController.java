package com.gosang.controller;


import com.gosang.entity.User;
import com.gosang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/register")
    public String register(User user){
        userService.save(user);
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

