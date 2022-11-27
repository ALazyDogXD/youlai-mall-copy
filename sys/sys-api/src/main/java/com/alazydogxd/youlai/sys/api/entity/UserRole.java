package com.alazydogxd.youlai.sys.api.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author ALazyDogXD
 * @date 2022/6/16 7:36
 * @description 用户角色
 */

public class UserRole implements Serializable {

    /** ID */
    private Integer id;

    /** 部门 ID */
    private Integer deptId;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 1-启用 0-禁用 */
    private Boolean status;

    /** 角色编码 */
    private List<String> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", deptId=" + deptId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", roles=" + roles +
                '}';
    }
}
