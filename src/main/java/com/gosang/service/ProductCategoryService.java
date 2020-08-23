package com.gosang.service;

import com.gosang.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gosang.vo.ProductCategoryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gosang
 * @since 2020-08-17
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    List<ProductCategoryVo> findAllProductCategoryVo();

}
