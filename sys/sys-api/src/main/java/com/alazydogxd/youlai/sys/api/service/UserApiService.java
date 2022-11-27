package com.alazydogxd.youlai.sys.api.service;

import com.alazydogxd.youlai.sys.api.entity.UserRole;

/**
 * @author ALazyDogXD
 * @date 2022/6/16 7:35
 * @description 用户 Api 服务
 */

public interface UserApiService {

    /**
     * 获取用户角色信息
     *
     * @param username 用户名
     * @return 用户角色信息
     */
    UserRole getUserRoleByUsername(String username);

}
