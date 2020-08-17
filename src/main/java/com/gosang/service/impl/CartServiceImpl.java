package com.gosang.service.impl;

import com.gosang.entity.Cart;
import com.gosang.mapper.CartMapper;
import com.gosang.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gosang
 * @since 2020-08-17
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

}
