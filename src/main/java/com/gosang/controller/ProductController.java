package com.gosang.controller;


import com.gosang.service.ProductCategoryService;
import com.gosang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("//product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list/{type}/{id}")
    public ModelAndView findProductByLevelId(@PathVariable("type") Integer type,@PathVariable("id") Integer levelId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        modelAndView.addObject("productList",productService.findProductVoByLevelId(type,levelId));
        modelAndView.addObject("ProductCategoryList",productCategoryService.findAllProductCategoryVo());
        return modelAndView;
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findProductById(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productDetail");
        modelAndView.addObject("product",productService.findProductById(id));
        modelAndView.addObject("ProductCategoryList",productCategoryService.findAllProductCategoryVo());
        return modelAndView;
    }

}

