package com.alazydogxd.youlai.sys.boot.entity.vo;

import com.alazydogxd.youlai.copy.common.base.convert.ObjectConvert;
import com.alazydogxd.youlai.copy.common.base.convert.TreeNode;
import com.alazydogxd.youlai.sys.boot.entity.MenuDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ALazyDogXD
 * @date 2022/6/19 8:50
 * @description 菜单
 */

@ApiModel("菜单")
public class MenuRoleVO implements TreeNode<MenuRoleVO>, ObjectConvert<MenuDO> {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("P_ID")
    private Integer parentId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("组件")
    private String component;

    @ApiModelProperty("重定向路径")
    private String redirect;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("菜单对应的角色集合")
    private List<String> roleCodes;

    @ApiModelProperty("子节点")
    private List<MenuRoleVO> children;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getParentId() {
        return parentId;
    }

    @Override
    public int getSort() {
        return -1;
    }

    @Override
    public void setChildren(List<MenuRoleVO> children) {
        this.children = children;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(List<String> roleCodes) {
        this.roleCodes = roleCodes;
    }

    public List<MenuRoleVO> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "MenuVO{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", redirect='" + redirect + '\'' +
                ", icon='" + icon + '\'' +
                ", roles=" + roleCodes +
                ", children=" + children +
                '}';
    }
}
