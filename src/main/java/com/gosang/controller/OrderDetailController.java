package com.gosang.controller;


import com.gosang.entity.User;
import com.gosang.service.CartService;
import com.gosang.service.OrderDetailService;
import com.gosang.vo.CartVo;
import com.gosang.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gosang
 * @since 2020-08-17
 */
@Controller
@RequestMapping("//orderDetail")
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    CartService cartService;


    @GetMapping("/orderAll")
    public ModelAndView orderAll(HttpSession session){
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderList");
        List<OrderVo> orderVos = orderDetailService.orderAll(user);
        List<CartVo> allCartVo = new ArrayList();
        if (user != null){
            allCartVo = cartService.findAllCart(user.getId());
        }
        modelAndView.addObject("user",user);
        modelAndView.addObject("orderVos",orderVos);
        modelAndView.addObject("CartVoList", allCartVo);
        return modelAndView;
    }
}

