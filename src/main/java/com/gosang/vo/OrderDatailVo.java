package com.gosang.vo;

import lombok.Data;

/**
 * @author gosang
 * @version 1.0
 * @date 2020/8/30 22:16
 */
@Data
public class OrderDatailVo {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 价格
     */
    private Float price;

    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 单类商品的总金额
     */
    private Float cost;
}
