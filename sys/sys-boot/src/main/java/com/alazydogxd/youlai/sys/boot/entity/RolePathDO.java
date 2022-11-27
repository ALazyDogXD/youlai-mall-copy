package com.alazydogxd.youlai.sys.boot.entity;

import java.util.List;

/**
 * @author ALazyDogXD
 * @date 2022/6/18 8:58
 * @description 角色路径关联
 */

public class RolePathDO extends RoleDO {

    /** 请求路径 */
    private List<String> paths;

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    @Override
    public String toString() {
        return super.toString() + "RolePathDO{" +
                "paths=" + paths +
                '}';
    }
}
