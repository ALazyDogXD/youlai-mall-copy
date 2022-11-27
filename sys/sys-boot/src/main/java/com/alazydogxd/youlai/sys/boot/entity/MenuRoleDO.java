package com.alazydogxd.youlai.sys.boot.entity;

import java.util.List;

/**
 * @author ALazyDogXD
 * @date 2022/6/19 8:02
 * @description 菜单角色关联
 */

public class MenuRoleDO extends MenuDO {

    /** 角色编码 */
    private List<String> roleCodes;

    public List<String> getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(List<String> roleCodes) {
        this.roleCodes = roleCodes;
    }

    @Override
    public String toString() {
        return super.toString() + "MenuRoleDO{" +
                "roleCodes=" + roleCodes +
                '}';
    }
}
