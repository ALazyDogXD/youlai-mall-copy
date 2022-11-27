package com.alazydogxd.youlai.common.validation.validator;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alazydogxd.youlai.common.validation.validator.anno.IsExist;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ALazyDogXD
 * @date 2022/6/24 6:23
 * @description 字段检测
 */

public class FieldValidator implements ConstraintValidator<IsExist, Object> {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private Class<?> type;

    private String column;

    private boolean enableSkip;

    private String skip;

    private String sql;

    @Override
    public void initialize(IsExist constraintAnnotation) {
        type = constraintAnnotation.type();
        column = constraintAnnotation.column();
        enableSkip = constraintAnnotation.enableSkip();
        skip = constraintAnnotation.skip();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (enableSkip && value.equals(Convert.convert(value.getClass(), skip))) {
            return true;
        }
        AtomicBoolean flag = new AtomicBoolean(false);
        try (SqlSession sqlSession = SqlHelper.sqlSession(type)) {
            sqlSession.select(getSql(), value, resultContext -> flag.set(resultContext.getResultCount() != 0));
        }
        return flag.get();
    }

    private String getSql() {
        if (StrUtil.isBlank(sql)) {
            TableInfo table = SqlHelper.table(type);
            String tableName = table.getTableName();
            sql = String.format("SELECT %s FROM %s WHERE %s = #{%s} limit 0, 1", column, tableName, column, column);
        }
        return sql;
    }

}
