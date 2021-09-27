package com.atguigu.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品三级分类
 *
 * @author lojoyo
 * @email lojoyo@yeah.net
 * @date 2021-09-09 21:28:31
 */
@Data
@TableName("pms_category")
public class CategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @TableId
    private Long catId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父分类id
     */
    private Long parentCid;

    /**
     * 层级
     */
    private Integer catLevel;

    /**
     * 是否显示[0-不显示，1显示]，逻辑删除注解@TableLogic
     */
    @TableLogic(value = "1", delval = "0")
    private Integer showStatus;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 图标地址
     */
    private String icon;

    /**
     * 计量单位
     */
    private String productUnit;

    /**
     * 商品数量
     */
    private Integer productCount;

    /**
     * 子分类（数据表不存在该字段）
     */
    @TableField(exist = false)
    private List<CategoryEntity> children;

}
