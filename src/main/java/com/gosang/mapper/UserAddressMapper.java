package com.gosang.mapper;

import com.gosang.entity.User;
import com.gosang.entity.UserAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gosang
 * @since 2020-08-17
 */
public interface UserAddressMapper extends BaseMapper<UserAddress> {
    int updateAddress(Integer userId);
}
