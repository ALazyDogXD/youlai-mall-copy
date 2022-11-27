package com.alazydogxd.youlai.sys.boot.dubbo;

import com.alazydogxd.youlai.sys.api.entity.UserRole;
import com.alazydogxd.youlai.sys.api.service.UserApiService;
import com.alazydogxd.youlai.sys.boot.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;

/**
 * @author ALazyDogXD
 * @date 2022/6/16 7:40
 * @description 用户 Api 服务
 */

@DubboService
public class UserApiServiceImpl implements UserApiService {

    private final UserService userService;

    public UserApiServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserRole getUserRoleByUsername(String username) {
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(userService.getUserRoleByUsername(username), userRole);
        return userRole;
    }
}
