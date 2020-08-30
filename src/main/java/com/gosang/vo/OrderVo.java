package com.gosang.vo;

import lombok.Data;

import java.util.List;

/**
 * @author gosang
 * @version 1.0
 * @date 2020/8/30 22:00
 */
@Data
public class OrderVo {

    private String userName;
    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 总金额
     */
    private Float cost;

    /**
     * 订单号
     */
    private String serialnumber;

    private List<OrderDatailVo> OrderDatailList;
}
