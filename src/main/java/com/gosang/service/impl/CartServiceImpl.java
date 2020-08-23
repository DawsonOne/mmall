package com.gosang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gosang.entity.Cart;
import com.gosang.entity.Product;
import com.gosang.mapper.CartMapper;
import com.gosang.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gosang.service.ProductService;
import com.gosang.vo.CartVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductService productService;

    @Override
    public List<CartVo> findAllCart(Integer userId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",userId);
        List<Cart> allCart = cartMapper.selectList(wrapper);
        List<CartVo> cartVoList = new ArrayList();
        CartVo cartVo = null;
        for (Cart cart : allCart) {
            cartVo = new CartVo();
            //根据商品Id拿到对应的商品信息
            Product product = productService.findProductById(cart.getProductId());
            BeanUtils.copyProperties(cart,cartVo);
            BeanUtils.copyProperties(product,cartVo);
            cartVoList.add(cartVo);
        }
        return cartVoList;
    }
}
