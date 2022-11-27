package com.alazydogxd.youlai.sys.boot.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.OffsetDateTime;

/**
 * @author ALazyDogXD
 * @date 2022/6/19 6:58
 * @description 菜单
 */

@TableName("sys_menu")
public class MenuDO {

    private Integer id;

    private String name;

    private Integer parentId;

    private String path;

    private String component;

    private String icon;

    private String redirect;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
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
        return "MenuDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", icon='" + icon + '\'' +
                ", redirect='" + redirect + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
