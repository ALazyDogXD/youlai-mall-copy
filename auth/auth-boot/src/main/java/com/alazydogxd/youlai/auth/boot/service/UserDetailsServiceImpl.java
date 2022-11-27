package com.alazydogxd.youlai.auth.boot.service;

import com.alazydogxd.youlai.auth.boot.constant.ClientIds;
import com.alazydogxd.youlai.auth.boot.entity.SysUserDetails;
import com.alazydogxd.youlai.sys.api.entity.UserRole;
import com.alazydogxd.youlai.sys.api.service.UserApiService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.alazydogxd.youlai.auth.boot.util.AuthRequestUtil.getClientId;
import static com.alazydogxd.youlai.copy.common.base.resp.ResponseStatus.USER_NOT_EXIST;

/**
 * @author ALazyDogXD
 * @date 2022/6/12 17:59
 * @description 系统用户详情服务
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @DubboReference
    private UserApiService userApiService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = getClientId();
        UserDetails userDetails;
        switch (clientId) {
            case ClientIds.ADMIN_WEB:
            default:
                userDetails = loadSysUserByUsername(username);
                break;
        }
        return userDetails;
    }

    private UserDetails loadSysUserByUsername(String username) {
        UserRole userRole = userApiService.getUserRoleByUsername(username);
        SysUserDetails userDetails = new SysUserDetails(userRole);
        if (userRole == null) {
            throw new UsernameNotFoundException(USER_NOT_EXIST.getMsg());
        } else if (!userDetails.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return userDetails;
    }

}
