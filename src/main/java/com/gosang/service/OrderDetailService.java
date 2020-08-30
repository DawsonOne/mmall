package com.gosang.service;

import com.gosang.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gosang.entity.User;
import com.gosang.vo.OrderVo;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gosang
 * @since 2020-08-17
 */
public interface OrderDetailService extends IService<OrderDetail> {
    List<OrderVo> orderAll(User user);
}
