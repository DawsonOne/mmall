package com.gosang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gosang.entity.OrderDetail;
import com.gosang.entity.Orders;
import com.gosang.entity.Product;
import com.gosang.entity.User;
import com.gosang.mapper.OrderDetailMapper;
import com.gosang.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gosang.service.OrdersService;
import com.gosang.service.ProductService;
import com.gosang.vo.OrderDatailVo;
import com.gosang.vo.OrderVo;
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
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    @Autowired
    OrdersService ordersService;
    @Autowired
    OrderDetailMapper orderDetailMapper;
    @Autowired
    ProductService productService;

    @Override
    public List<OrderVo> orderAll(User user) {
        OrderVo orderVo = null;
        List<OrderVo> OrderVolList = new ArrayList<>();
        List<Orders> allOrders = ordersService.findAllOrders(user.getId());
        for (Orders Order : allOrders) {
            orderVo = new OrderVo();
            orderVo.setUserName(user.getUserName());
            BeanUtils.copyProperties(Order,orderVo);

            //查找相应的商品详情
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("order_id",Order.getId());
            List<OrderDetail> OrderDetailList = orderDetailMapper.selectList(queryWrapper);
            OrderDatailVo orderDatailVo = null;
            List<OrderDatailVo> OrderDatailList = new ArrayList<>();
            for (OrderDetail orderDetail : OrderDetailList) {
                orderDatailVo = new OrderDatailVo();
                BeanUtils.copyProperties(orderDetail,orderDatailVo);
                Product product = productService.findProductByProductId(orderDetail.getProductId());
                BeanUtils.copyProperties(product,orderDatailVo);
                OrderDatailList.add(orderDatailVo);
            }
            orderVo.setOrderDatailList(OrderDatailList);
            OrderVolList.add(orderVo);
        }
        return OrderVolList;
    }
}
