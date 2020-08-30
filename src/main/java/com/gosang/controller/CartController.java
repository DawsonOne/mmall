package com.gosang.controller;


import com.gosang.entity.Cart;
import com.gosang.entity.Product;
import com.gosang.entity.User;
import com.gosang.entity.UserAddress;
import com.gosang.service.CartService;
import com.gosang.service.ProductService;
import com.gosang.service.UserAddressService;
import com.gosang.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
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
    @Autowired
    ProductService productService;
    @Autowired
    UserAddressService userAddressService;

    @GetMapping("/add/{id}/{price}/{quantity}")
    public String addCart(@PathVariable("id") Integer id, @PathVariable("price") float price, @PathVariable("quantity") Integer quantity, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Cart cart = new Cart();
        Float cost = price * quantity;
        cart.setProductId(id);
        cart.setUserId(user.getId());
        cart.setCost(cost);
        cart.setQuantity(quantity);
        //减库存
        Product product = productService.getById(id);
        product.setStock(product.getStock() - quantity);
        productService.updateById(product);
        boolean save = cartService.save(cart);
        return "redirect:/cart/findAll";

    }

    @GetMapping("/findAll")
    public ModelAndView findAllCart(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement1");
        //根据用户id拿到其对应的购物车信息
        List<CartVo> allCartVo = cartService.findAllCart(user.getId());
        modelAndView.addObject("CartVoList", allCartVo);
        return modelAndView;
    }

    @PostMapping("/updateCart/{type}/{id}/{productId}/{quantity}/{cost}")
    @ResponseBody
    public String subOrAddCart(@PathVariable("type") String type,@PathVariable("id") Integer id, @PathVariable("productId") Integer productId, @PathVariable("quantity") Integer quantity, @PathVariable("cost") Float cost) {
        Boolean aBoolean = cartService.subOrAddCart(type, id, productId, quantity, cost);
        if(aBoolean) return "success";
        return "fail";
    }

    @GetMapping("/removeCart/{id}")
    public String removeCart(@PathVariable("id") Integer id){
        Cart cart = cartService.getById(id);
        Product productById = productService.findProductById(cart.getProductId());
        productById.setStock(productById.getStock()+cart.getQuantity());
        productService.updateById(productById);
        cartService.removeCart(id);
        return "redirect:/cart/findAll";
    }

    @GetMapping("/goToSettlement")
    public ModelAndView goToSettlement(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<CartVo> allCart = cartService.findAllCart(user.getId());
        List<UserAddress> allUserAddress = userAddressService.findAllUserAddress(user.getId());
        List<CartVo> allCartVo = new ArrayList();
        if (user != null){
            allCartVo = cartService.findAllCart(user.getId());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement2");
        modelAndView.addObject("user",user);
        modelAndView.addObject("allCart",allCart);
        modelAndView.addObject("allUserAddress",allUserAddress);
        modelAndView.addObject("CartVoList", allCartVo);
        return modelAndView;
    }
}

