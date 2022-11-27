package com.alazydogxd.youlai.sys.boot.service;

import com.alazydogxd.youlai.sys.boot.entity.RoleDO;
import com.alazydogxd.youlai.sys.boot.entity.RolePathDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author ALazyDogXD
 * @date 2022/6/18 8:47
 * @description 角色服务
 */

public interface RoleService extends IService<RoleDO> {

    /**
     * 获取角色路径权限
     *
     * @param roleCodes 角色编码
     * @return 角色路径权限
     */
    List<RolePathDO> getRolePathByRoleCodes(List<String> roleCodes);

    /**
     * 获取角色编码
     *
     * @param userId 用户 ID
     * @return 角色编码
     */
    List<String> getRoleCodesByUserId(int userId);

}
