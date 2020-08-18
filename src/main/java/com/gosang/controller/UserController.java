package com.gosang.controller;


import com.gosang.Exception.MMallException;
import com.gosang.entity.User;
import com.gosang.enums.ExceptionEnum;
import com.gosang.enums.GenderEnum;
import com.gosang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/register")
    public String register(User user){
        if (user == null){
            throw new MMallException(ExceptionEnum.USER_NOT_EXISt);
        }
        if (user.getSex() == 0){
            user.setGender(GenderEnum.MALE);
        }else {
            user.setGender(GenderEnum.FEMALE);
        }
        boolean save = userService.save(user);
        if (!save){
            throw new MMallException(ExceptionEnum.REGISTER_FAIL);
        }
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

