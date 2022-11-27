package com.alazydogxd.youlai.sys.boot.service.impl;

import com.alazydogxd.youlai.sys.boot.entity.MenuDO;
import com.alazydogxd.youlai.sys.boot.entity.MenuRoleDO;
import com.alazydogxd.youlai.sys.boot.mapper.MenuMapper;
import com.alazydogxd.youlai.sys.boot.service.MenuService;
import com.alazydogxd.youlai.sys.boot.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.alazydogxd.youlai.copy.common.base.constant.Securities.ROLE_ROOT;

/**
 * @author ALazyDogXD
 * @date 2022/6/19 7:35
 * @description 菜单服务
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuDO> implements MenuService {

    private final RoleService roleService;

    public MenuServiceImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public List<MenuRoleDO> getMenuRoleByUserId(int userId) {
        List<MenuRoleDO> menuRoles;
        QueryWrapper<Object> wrapper = new QueryWrapper<>().select(
                "m.id id",
                "m.parent_id parent_id",
                "m.name as name",
                "m.path path",
                "m.component component",
                "m.icon icon",
                "m.redirect redirect",
                "r.code code");

        // root 用户拥有所有权限
        List<String> roleCodes = roleService.getRoleCodesByUserId(userId);
        if (roleCodes.contains(ROLE_ROOT)) {
            menuRoles = baseMapper.getByRoleCodes(null, wrapper);
        } else {
            menuRoles = baseMapper.getByRoleCodes(roleCodes, wrapper);
        }
        return menuRoles;
    }
}
