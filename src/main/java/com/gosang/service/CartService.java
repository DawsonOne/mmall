package com.gosang.service;

import com.gosang.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gosang.vo.CartVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gosang
 * @since 2020-08-17
 */
public interface CartService extends IService<Cart> {
    List<CartVo> findAllCart(Integer userId);


}
