package com.gosang.service;

import com.gosang.entity.Product;
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
public interface ProductService extends IService<Product> {
    List<Product> findProductVoByLevelId(Integer type,Integer levelId);
    Product findProductById(Integer id);
    Product findProductByProductId(Integer proId);
}
