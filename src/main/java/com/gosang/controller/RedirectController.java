package com.gosang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author gosang
 * @version 1.0
 * @date 2020/8/18 0:03
 */
@Controller
public class RedirectController {

    @GetMapping("/")
    public String redirectMain(){
        return "redirect:/productCategory/CategoryList";
    }

    /*这样所有请求都得走后台了，即使直接访问前端页面，也得走后台，经过试图解析器就能直接出来了。不然直接访问前端页面样式出不来,
    因为thymeleaf模板代码html解析不出来，必须走后台经过视图解析器才能翻译成html*/
    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url){
        return url;
    }
}
