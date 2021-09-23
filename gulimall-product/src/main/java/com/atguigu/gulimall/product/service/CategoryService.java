package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.common.utils.PageUtils;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author lojoyo
 * @email lojoyo@yeah.net
 * @date 2021-09-09 21:28:31
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询所有分类以及子类，以树型结构组装起来
     * @return
     */
    List<CategoryEntity> listWithTree();
}

