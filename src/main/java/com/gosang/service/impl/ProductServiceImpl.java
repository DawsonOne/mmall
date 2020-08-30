package com.gosang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gosang.entity.Product;
import com.gosang.mapper.ProductMapper;
import com.gosang.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    ProductMapper productMapper;

    /**
     * 通过分类id找到对应的商品
     * @param
     * @return
     */
    @Override
    public List<Product> findProductVoByLevelId(Integer type,Integer levelThreeId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        switch (type){
            case 1:
                queryWrapper.eq("categorylevelone_id",levelThreeId);
                break;
            case 2:
                queryWrapper.eq("categoryleveltwo_id",levelThreeId);
                break;
            case 3:
                queryWrapper.eq("categorylevelthree_id",levelThreeId);
                break;
        }
        List<Product> productList = productMapper.selectList(queryWrapper);
        return productList;
    }

    @Override
    public Product findProductById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",id);
        return productMapper.selectById(id);
    }

    @Override
    public Product findProductByProductId(Integer proId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",proId);
        return productMapper.selectOne(queryWrapper);
    }

}
