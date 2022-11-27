package com.alazydogxd.youlai.sys.boot.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.OffsetDateTime;

/**
 * @author ALazyDogXD
 * @date 2022/6/14 7:38
 * @description 角色
 */

@TableName("sys_role")
public class RoleDO {

    private Integer id;

    private String name;

    private String code;

    private Boolean status;

    private Boolean deleted;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
        return "RoleDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", status=" + status +
                ", deleted=" + deleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
