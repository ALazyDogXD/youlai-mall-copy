package com.alazydogxd.youlai.sys.boot.mapper;

import com.alazydogxd.youlai.sys.boot.entity.MenuDO;
import com.alazydogxd.youlai.sys.boot.entity.MenuRoleDO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import static com.baomidou.mybatisplus.core.toolkit.Constants.WRAPPER;

/**
 * @author ALazyDogXD
 * @date 2022/6/19 7:28
 * @description 菜单 Mapper
 */

public interface MenuMapper extends BaseMapper<MenuDO> {

    /**
     * 获取菜单
     *
     * @param roleCodes 角色编码
     * @param wrapper   条件
     * @return 菜单
     */
    List<MenuRoleDO> getByRoleCodes(@Param("roleCodes") List<String> roleCodes, @Param(WRAPPER) Wrapper<?> wrapper);

}
