package com.alazydogxd.youlai.auth.boot.entity;

import com.alazydogxd.youlai.sys.api.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.alazydogxd.youlai.auth.boot.enums.PasswordEncoderType.BCRYPT;

/**
 * @author ALazyDogXD
 * @date 2022/6/12 22:07
 * @description 系统用户详情
 */

public class SysUserDetails implements UserDetails {

    private String userId;
    private String deptId;
    private String authenticationIdentity;

    private String username;
    private String password;
    private Boolean enabled;
    private Collection<SimpleGrantedAuthority> authorities;

    public SysUserDetails(UserRole userRole) {
        if (userRole == null) {
            return;
        }
        userId = userRole.getId().toString();
        deptId = userRole.getDeptId().toString();
        username = userRole.getUsername();
        password = BCRYPT.getPrefix() + userRole.getPassword();
        enabled = userRole.getStatus();
        authorities = userRole.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getAuthenticationIdentity() {
        return authenticationIdentity;
    }

    public void setAuthenticationIdentity(String authenticationIdentity) {
        this.authenticationIdentity = authenticationIdentity;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
