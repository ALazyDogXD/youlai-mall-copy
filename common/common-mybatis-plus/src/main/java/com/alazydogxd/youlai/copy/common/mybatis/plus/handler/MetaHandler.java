package com.alazydogxd.youlai.copy.common.mybatis.plus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.OffsetDateTime;

/**
 * @author ALazyDogXD
 * @date 2022/7/10 9:18
 * @description 数据填充
 */

public class MetaHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        strictInsertFill(metaObject, "gmtCreate", OffsetDateTime.class, OffsetDateTime.now());
        strictInsertFill(metaObject, "gmtModified", OffsetDateTime.class, OffsetDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictUpdateFill(metaObject, "gmtModified", OffsetDateTime.class, OffsetDateTime.now());
    }
}
