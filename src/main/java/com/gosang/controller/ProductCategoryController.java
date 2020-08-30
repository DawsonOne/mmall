package com.gosang.controller;


import com.gosang.entity.User;
import com.gosang.service.CartService;
import com.gosang.service.ProductCategoryService;
import com.gosang.vo.CartVo;
import com.gosang.vo.ProductCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("//productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    CartService cartService;

    @GetMapping("/CategoryList")
    public ModelAndView findAllProductCategoryVo(HttpSession session){

        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        //根据用户id拿到其对应的购物车信息
        List<CartVo> allCartVo = new ArrayList();
        if (user != null){
            allCartVo = cartService.findAllCart(user.getId());
        }
        modelAndView.addObject("CartVoList", allCartVo);
        modelAndView.addObject("ProductCategoryList",productCategoryService.findAllProductCategoryVo());
       return modelAndView;

    }
}

