package com.gosang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gosang.entity.Product;
import com.gosang.entity.ProductCategory;
import com.gosang.mapper.ProductCategoryMapper;
import com.gosang.service.ProductCategoryService;
import com.gosang.service.ProductService;
import com.gosang.vo.ProductCategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    ProductCategoryMapper productCategoryMapper;
    @Autowired
    ProductService productService;

    @Override
    public List<ProductCategoryVo> findAllProductCategoryVo() {
        //最终返回的结果
        List<ProductCategoryVo> result = new ArrayList();
        //一级分类取得
        List<ProductCategory> levelOneList = QueryProductCategoryByLevel(1,0);
        //一级分类的VO定义
        ProductCategoryVo productCategoryVoOne;

        //图片
        int num = 0;
        for (ProductCategory productCategoryOne : levelOneList) {
            productCategoryVoOne = new ProductCategoryVo();
            /*productCategoryVoOne.setId(productCategory.getId());
            productCategoryVoOne.setName(productCategory.getName());*/
            BeanUtils.copyProperties(productCategoryOne,productCategoryVoOne);
            productCategoryVoOne.setBanner("banner"+num);
            productCategoryVoOne.setTop("top"+num);
            num++;
            //一级分类商品取得
            List<Product> productList = productService.findProductVoByLevelId(1, productCategoryOne.getId());
            productCategoryVoOne.setProductList(productList);

            //二级分类取得
            List<ProductCategory> levelTwoList = QueryProductCategoryByLevel(2,productCategoryOne.getId());
            //二级分类的VO定义
            ProductCategoryVo productCategoryVoTwo;
            List<ProductCategoryVo> levelTwoVoList = new ArrayList();
            for (ProductCategory productCategoryTwo : levelTwoList) {
                productCategoryVoTwo = new ProductCategoryVo();
                BeanUtils.copyProperties(productCategoryTwo,productCategoryVoTwo);

                //三级分类取得
                List<ProductCategory> levelThreeList = QueryProductCategoryByLevel(3,productCategoryTwo.getId());
                //三级分类的VO定义
                ProductCategoryVo productCategoryVoThree;
                List<ProductCategoryVo> levelThreeVoList = new ArrayList();
                for (ProductCategory productCategoryThree : levelThreeList) {
                    productCategoryVoThree = new ProductCategoryVo();
                    BeanUtils.copyProperties(productCategoryThree,productCategoryVoThree);
                    levelThreeVoList.add(productCategoryVoThree);
                }
                productCategoryVoTwo.setChildern(levelThreeVoList);

                levelTwoVoList.add(productCategoryVoTwo);
            }
            productCategoryVoOne.setChildern(levelTwoVoList);
            result.add(productCategoryVoOne);
        }

        return result;
    }


    public List<ProductCategory> QueryProductCategoryByLevel(Integer type,Integer parentId){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("type",type);
        wrapper.eq("parent_id",parentId);
        return productCategoryMapper.selectList(wrapper);

    }
}
