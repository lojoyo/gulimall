package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.common.utils.PageUtils;
import com.atguigu.gulimall.common.utils.Query;
import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 查询所有分类以及子类，以树型结构组装起来
     *
     * @return
     */
    @Override
    public List<CategoryEntity> listWithTree() {
        // 1.查询所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);

        // 2.组转成父子的树形结构
        // 2.1 找到所有一级分类
        List<CategoryEntity> level1Menus = entities.stream().filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .map((menu) -> {
                    // 找到子菜单
                    menu.setChildren(getChildrens(menu, entities));
                    return menu;
                }).sorted((menu1, menu2) -> {
                    // 菜单排序
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                .collect(Collectors.toList());

        return level1Menus;
    }


    /**
     * 递归查找当前菜单所有的子菜单
     *
     * @param root 父菜单
     * @param all  所有菜单
     * @return
     */
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
                    // 1.找到当前父菜单
                    return categoryEntity.getParentCid() == root.getCatId();
                })
                .map((categoryEntity) -> {
                    // 2.找到子菜单，子菜单也会递归找到自己的子菜单
                    categoryEntity.setChildren(getChildrens(categoryEntity, all));
                    return categoryEntity;
                })
                .sorted((menu1, menu2) -> {
                    // 3.菜单的排序
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                .collect(Collectors.toList());

        return children;
    }

}