package com.gosang.service;

import com.gosang.entity.UserAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gosang
 * @since 2020-08-17
 */
public interface UserAddressService extends IService<UserAddress> {
        List<UserAddress> findAllUserAddress(Integer userId);
}
