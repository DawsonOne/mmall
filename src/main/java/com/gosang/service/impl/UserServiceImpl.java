package com.gosang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gosang.Exception.MMallException;
import com.gosang.entity.User;
import com.gosang.enums.ExceptionEnum;
import com.gosang.enums.GenderEnum;
import com.gosang.mapper.UserMapper;
import com.gosang.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserMapper userMapper;

    @Override
    public Boolean register(User user) {
        if (user == null){
            throw new MMallException(ExceptionEnum.USER_NOT_EXISt);
        }
        if (user.getSex() == 0){
            user.setGender(GenderEnum.FEMALE);
        }else {
            user.setGender(GenderEnum.MALE);
        }
        int insert = userMapper.insert(user);
        if(insert == 1)return true;
        return false;
    }

    @Override
    public User login(User user) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("login_name",user.getLoginName());
        wrapper.eq("password",user.getPassword());
        User user1 = userMapper.selectOne(wrapper);
        return user1;
    }
}
