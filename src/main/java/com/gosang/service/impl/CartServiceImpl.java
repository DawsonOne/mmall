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
            BeanUtils.copyProperties(product,cartVo);
            BeanUtils.copyProperties(cart,cartVo);
            cartVoList.add(cartVo);
        }
        return cartVoList;
    }

    @Override
    public Boolean subOrAddCart(String type, Integer id, Integer productId, Integer quantity, Float cost) {
        boolean b = false;
        Integer cartup;
        Integer proup;
        Integer quantity1;
        Float cost1 = 0F;
        Integer stock;
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",id);
        Cart cart = cartMapper.selectOne(wrapper);
        Product product = productService.findProductById(productId);
        switch (type){
            case "sub":
                //减少的个数
                quantity1 = cart.getQuantity() - quantity;
                //通过减少的个数得到库存应加的个数
                stock = product.getStock() + quantity1;
                //更新购物车表cart
                cart.setQuantity(quantity);
                cart.setCost(cost);
                cartup = cartMapper.updateById(cart);
                //更新库存，product表
                product.setStock(stock);
                b = productService.updateById(product);
                break;
            case "add":
                //增加的个数
                quantity1 = cart.getQuantity() + quantity;
                //通过增加的个数得到库存应减少的个数
                stock = product.getStock() - quantity1;
                //更新购物车表cart
                cart.setQuantity(quantity);
                cart.setCost(cost);
                cartup = cartMapper.updateById(cart);
                //更新库存，product表
                product.setStock(stock);
                b = productService.updateById(product);
                break;
        }
        return b;
    }

    @Override
    public String removeCart(Integer id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",id);
        cartMapper.delete(wrapper);
        return null;
    }
}
