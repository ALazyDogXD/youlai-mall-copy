package com.alazydogxd.youlai.sys.boot.entity.vo;

import com.alazydogxd.youlai.copy.common.base.convert.ObjectConvert;
import com.alazydogxd.youlai.sys.boot.entity.UserRoleDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ALazyDogXD
 * @date 2022/6/17 7:52
 * @description 登录用户
 */

@ApiModel("当前登录用户")
public class LoginUserVO implements ObjectConvert<UserRoleDO> {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("头像地址")
    private String avatar;

    @ApiModelProperty("用户的角色编码集合")
    private List<String> roles;

    @ApiModelProperty("用户的按钮权限标识集合")
    private List<String> perms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPerms() {
        return perms;
    }

    public void setPerms(List<String> perms) {
        this.perms = perms;
    }

    @Override
    public String toString() {
        return "LoginUserVO{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", roles=" + roles +
                ", perms=" + perms +
                '}';
    }
}
