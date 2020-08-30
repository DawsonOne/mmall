package com.gosang.service;

import com.gosang.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gosang.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gosang
 * @since 2020-08-17
 */
public interface OrdersService extends IService<Orders> {
    Orders addOrder(User user, String selectAddress, Float cost,String address,String remark);
    List<Orders> findAllOrders(Integer userId);
}
