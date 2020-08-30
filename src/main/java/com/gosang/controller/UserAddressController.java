package com.gosang.controller;


import com.gosang.entity.User;
import com.gosang.entity.UserAddress;
import com.gosang.service.CartService;
import com.gosang.service.UserAddressService;
import com.gosang.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("//userAddress")
public class UserAddressController {

    @Autowired
    UserAddressService userAddressService;
    @Autowired
    CartService cartService;

    @GetMapping("/userAddressList")
    public ModelAndView userAddressList(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<UserAddress> allUserAddress = userAddressService.findAllUserAddress(user.getId());
        List<CartVo> allCartVo = new ArrayList();
        if (user != null){
            allCartVo = cartService.findAllCart(user.getId());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userAddressList");
        modelAndView.addObject("allUserAddress",allUserAddress);
        modelAndView.addObject("CartVoList", allCartVo);
        return modelAndView;
    }
}

