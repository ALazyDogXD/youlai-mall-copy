package com.alazydogxd.youlai.sys.boot.service;

import com.alazydogxd.youlai.sys.boot.entity.UserDO;
import com.alazydogxd.youlai.sys.boot.entity.UserRoleDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author ALazyDogXD
 * @date 2022/6/14 22:11
 * @description 用户服务
 */

public interface UserService extends IService<UserDO> {

    /**
     * 获取用户角色信息
     *
     * @param username 用户名称
     * @return 用户角色信息
     */
    UserRoleDO getUserRoleByUsername(String username);

}
