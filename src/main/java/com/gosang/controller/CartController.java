package com.gosang.controller;


import com.gosang.entity.Cart;
import com.gosang.entity.Product;
import com.gosang.entity.User;
import com.gosang.service.CartService;
import com.gosang.service.ProductService;
import com.gosang.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("//cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/add/{id}/{price}/{quantity}")
    public String addCart(@PathVariable("id") Integer id, @PathVariable("price") float price, @PathVariable("quantity") Integer quantity, HttpSession session){
        User user = (User) session.getAttribute("user");
        Cart cart = new Cart();
        Float cost = price*quantity;
        cart.setProductId(id);
        cart.setUserId(user.getId());
        cart.setCost(cost);
        cart.setQuantity(quantity);
        boolean save = cartService.save(cart);
            return "redirect:/cart/findAll";

    }

    @GetMapping("/findAll")
    public ModelAndView findAllCart(HttpSession session){
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement1");
        //根据用户id拿到其对应的购物车信息
        List<CartVo> allCartVo = cartService.findAllCart(userId);
        modelAndView.addObject("CartVoList",allCartVo);
        return modelAndView;
    }
}

