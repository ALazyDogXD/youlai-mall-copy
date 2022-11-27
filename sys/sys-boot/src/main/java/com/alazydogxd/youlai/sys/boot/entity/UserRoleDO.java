package com.alazydogxd.youlai.sys.boot.entity;

import java.util.List;

/**
 * @author ALazyDogXD
 * @date 2022/6/14 7:38
 * @description 用户角色
 */

public class UserRoleDO extends UserDO {

    /** 角色编码 */
    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return super.toString() + "UserRoleDO{" +
                "roles=" + roles +
                '}';
    }
}
