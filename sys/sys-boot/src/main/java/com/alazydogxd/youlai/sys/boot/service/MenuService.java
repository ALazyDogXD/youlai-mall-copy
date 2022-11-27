package com.alazydogxd.youlai.sys.boot.service;

import com.alazydogxd.youlai.sys.boot.entity.MenuDO;
import com.alazydogxd.youlai.sys.boot.entity.MenuRoleDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author ALazyDogXD
 * @date 2022/6/19 7:30
 * @description 菜单服务
 */

public interface MenuService extends IService<MenuDO> {

    /**
     * 获取菜单
     *
     * @param userId 用户 ID
     * @return 菜单
     */
    List<MenuRoleDO> getMenuRoleByUserId(int userId);

}
