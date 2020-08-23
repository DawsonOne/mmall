package com.gosang.vo;

import com.gosang.entity.Product;
import lombok.Data;

import java.util.List;

/**
 * @author gosang
 * @version 1.0
 * @date 2020/8/20 22:31
 */
@Data
public class ProductCategoryVo {

    /**
     * 商品id
     */
    private Integer id;
    /**
     * 商品名
     */
    private String name;

    private List<ProductCategoryVo> childern;
    private String banner;
    private String top;
    private List<Product> productList;
}
