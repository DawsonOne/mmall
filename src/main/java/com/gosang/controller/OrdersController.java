package com.gosang.controller;


import com.gosang.entity.Orders;
import com.gosang.entity.User;
import com.gosang.service.CartService;
import com.gosang.service.OrdersService;
import com.gosang.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("//orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    CartService cartService;

    @PostMapping("/addOrder")
    public ModelAndView addOrder(HttpSession session,String selectAddress,Float cost,String address,String remark){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        Orders orders = ordersService.addOrder(user, selectAddress, cost, address, remark);
        List<CartVo> allCartVo = new ArrayList();
        if (user != null){
            allCartVo = cartService.findAllCart(user.getId());
        }
        modelAndView.setViewName("settlement3");
        modelAndView.addObject("orders",orders);
        modelAndView.addObject("user",user);
        modelAndView.addObject("CartVoList", allCartVo);
        return modelAndView;
    }
}

