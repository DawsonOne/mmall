package com.gosang.service.impl;

import com.gosang.entity.Product;
import com.gosang.mapper.ProductMapper;
import com.gosang.service.ProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
