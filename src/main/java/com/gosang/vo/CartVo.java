package com.gosang.vo;

import lombok.Data;

/**
 * @author gosang
 * @version 1.0
 * @date 2020/8/23 17:54
 */
@Data
public class CartVo {
    /**
     * 商品名称
     */
    private String name;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 价格
     */
    private Float price;

    /**
     * 库存
     */
    private Integer stock;

    private Integer quantity;

    private Float cost;
}
