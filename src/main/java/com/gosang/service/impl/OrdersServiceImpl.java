package com.gosang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gosang.entity.*;
import com.gosang.mapper.*;
import com.gosang.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gosang
 * @since 2020-08-17
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    OrderDetailMapper orderDetailMapper;
    @Autowired
    UserAddressMapper userAddressMapper;

    @Override
    public Orders addOrder(User user, String selectAddress, Float cost,String address,String remark) {
        //判断当前是新地址还是老地址
        if (selectAddress.equals("newAddress")){
            selectAddress = address;
            userAddressMapper.updateAddress(user.getId());
            //新增地址
            UserAddress userAddress = new UserAddress();
            userAddress.setAddress(selectAddress);
            userAddress.setRemark(remark);
            userAddress.setUserId(user.getId());
            userAddress.setIsdefault(1);
            userAddressMapper.insert(userAddress);
        }
        //存储Orders
        Orders orders = new Orders();
        //随机生成订单号
        String seriaNumber = null;
        try {
            StringBuffer result = new StringBuffer();
            for(int i=0;i<32;i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            seriaNumber =  result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        orders.setUserId(user.getId());
        orders.setLoginName(user.getLoginName());
        orders.setSerialnumber(seriaNumber);
        orders.setUserAddress(selectAddress);
        orders.setCost(cost);
        ordersMapper.insert(orders);
        //存储OrderDetail
        OrderDetail orderDetail = new OrderDetail();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",user.getId());
        List<Cart> list = cartMapper.selectList(queryWrapper);
        for (Cart cart : list) {
            orderDetail.setOrderId(orders.getId());
            BeanUtils.copyProperties(cart,orderDetail);
            orderDetailMapper.insert(orderDetail);
        }

        //清空购物车
        cartMapper.delete(queryWrapper);
        return orders;
    }


    public List<Orders> findAllOrders(Integer userId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        List OrderList = ordersMapper.selectList(queryWrapper);
        return OrderList;

    }
}
