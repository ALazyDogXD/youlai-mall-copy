package com.alazydogxd.youlai.shop.boot.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.OffsetDateTime;

/**
 * @author ALazyDogXD
 * @date 2022/6/21 7:30
 * @description 商品分类
 */

@TableName("shop_category")
public class CategoryDO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer parentId;

    private String iconUrl;

    private Integer sort;

    private Boolean visible;

    @TableField(fill = FieldFill.INSERT)
    private OffsetDateTime gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private OffsetDateTime gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public CategoryDO setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public OffsetDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(OffsetDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public OffsetDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(OffsetDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "CategoryDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", iconUrl='" + iconUrl + '\'' +
                ", sort=" + sort +
                ", visible=" + visible +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
