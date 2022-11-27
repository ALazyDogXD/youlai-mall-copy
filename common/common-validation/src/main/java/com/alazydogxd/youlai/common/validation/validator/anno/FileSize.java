package com.alazydogxd.youlai.common.validation.validator.anno;

import com.alazydogxd.youlai.common.validation.validator.FileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author ALazyDogXD
 * @date 2022/7/9 15:38
 * @description 缩略图验证
 */

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {FileValidator.class})
public @interface FileSize {

    // 文件大小
    long max() default (1 << 20) * 2;

    // 当验证不通过时的提示信息
    String message() default "文件过大";

    // 约束注解在验证时所属的组别
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};
}
