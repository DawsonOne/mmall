package com.gosang.service.impl;

import com.gosang.entity.Orders;
import com.gosang.mapper.OrdersMapper;
import com.gosang.service.OrdersService;
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
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
