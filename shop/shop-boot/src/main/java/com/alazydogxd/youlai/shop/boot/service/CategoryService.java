package com.alazydogxd.youlai.shop.boot.service;

import com.alazydogxd.youlai.shop.boot.entity.CategoryCreateDTO;
import com.alazydogxd.youlai.shop.boot.entity.CategoryDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author ALazyDogXD
 * @date 2022/6/21 7:34
 * @description 商品分类服务
 */

public interface CategoryService extends IService<CategoryDO> {

    /**
     * 添加分类
     *
     * @param category 分类入参
     */
    void add(CategoryCreateDTO category);

}
