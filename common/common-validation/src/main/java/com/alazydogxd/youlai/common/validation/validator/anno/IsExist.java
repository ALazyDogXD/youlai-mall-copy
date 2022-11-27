package com.alazydogxd.youlai.common.validation.validator.anno;

import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author ALazyDogXD
 * @date 2022/6/22 6:56
 * @description 数据库中数据是否存在
 */

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
//@Constraint(validatedBy = {FileValidator.class})
public @interface IsExist {

    // 用来验证数据的 DO 实体类
    Class<?> type();

    // 数据表中的列名
    String column();

    // 开始验证跳过
    boolean enableSkip() default false;

    // 当数值为 skip 时跳过验证
    String skip() default "";

    // 当验证不通过时的提示信息
    String message() default "数据不存在";

    // 约束注解在验证时所属的组别
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};

}
