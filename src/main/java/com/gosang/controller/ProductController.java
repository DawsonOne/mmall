package com.gosang.controller;


import com.gosang.entity.User;
import com.gosang.service.CartService;
import com.gosang.service.ProductCategoryService;
import com.gosang.service.ProductService;
import com.gosang.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("//product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    CartService cartService;

    @GetMapping("/list/{type}/{id}")
    public ModelAndView findProductByLevelId(@PathVariable("type") Integer type,@PathVariable("id") Integer levelId, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        User user = (User) session.getAttribute("user");
        List<CartVo> allCartVo = new ArrayList();
        if (user != null){
            allCartVo = cartService.findAllCart(user.getId());
        }
        modelAndView.addObject("CartVoList", allCartVo);
        modelAndView.addObject("productList",productService.findProductVoByLevelId(type,levelId));
        modelAndView.addObject("ProductCategoryList",productCategoryService.findAllProductCategoryVo());
        return modelAndView;
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findProductById(@PathVariable("id") Integer id, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productDetail");
        User user = (User) session.getAttribute("user");
        List<CartVo> allCartVo = new ArrayList();
        if (user != null){
            allCartVo = cartService.findAllCart(user.getId());
        }
        modelAndView.addObject("CartVoList", allCartVo);
        modelAndView.addObject("product",productService.findProductById(id));
        modelAndView.addObject("ProductCategoryList",productCategoryService.findAllProductCategoryVo());
        return modelAndView;
    }

}

