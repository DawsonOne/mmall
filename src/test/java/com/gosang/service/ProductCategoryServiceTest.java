package com.gosang.service;

import com.gosang.vo.ProductCategoryVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
 * @author gosang
 * @version 1.0
 * @date 2020/8/20 23:12
 */
@SpringBootTest
class ProductCategoryServiceTest {
    @Autowired
    public ProductCategoryService productCategoryService;

    @Test
    public List<ProductCategoryVo> test(){
        List<ProductCategoryVo> allProductCategoryVo = productCategoryService.findAllProductCategoryVo();
        return  allProductCategoryVo;
    }

}