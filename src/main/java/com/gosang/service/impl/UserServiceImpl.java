package com.gosang.service.impl;

import com.gosang.entity.User;
import com.gosang.mapper.UserMapper;
import com.gosang.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
