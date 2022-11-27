package com.alazydogxd.youlai.shop.boot.entity;

import com.alazydogxd.youlai.copy.common.base.convert.ObjectConvert;
import com.alazydogxd.youlai.copy.common.base.convert.TreeNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ALazyDogXD
 * @date 2022/6/21 7:39
 * @description 商品分类
 */

@ApiModel("商品分类")
public class CategoryVO implements ObjectConvert<CategoryDO>, TreeNode<CategoryVO> {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("P_ID")
    private Integer parentId;

    @ApiModelProperty("顺序")
    private Integer sort;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("层级")
    private Integer level;

    @ApiModelProperty("图标")
    private String iconUrl;

    @ApiModelProperty("显隐控制")
    private Integer visible;

    @ApiModelProperty("子节点")
    private List<CategoryVO> children;

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
        return sort;
    }

    @Override
    public void setChildren(List<CategoryVO> children) {
        this.children = children;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public List<CategoryVO> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "CategoryVO{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", sort=" + sort +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", iconUrl='" + iconUrl + '\'' +
                ", visible=" + visible +
                ", children=" + children +
                '}';
    }
}
