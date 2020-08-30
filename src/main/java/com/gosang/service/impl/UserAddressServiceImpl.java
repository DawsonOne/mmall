package com.gosang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gosang.entity.User;
import com.gosang.entity.UserAddress;
import com.gosang.mapper.UserAddressMapper;
import com.gosang.service.UserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

    @Autowired
    UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> findAllUserAddress(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        List<UserAddress> addressList = userAddressMapper.selectList(queryWrapper);
        return addressList;
    }
}
