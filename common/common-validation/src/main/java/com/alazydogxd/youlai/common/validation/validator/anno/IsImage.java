package com.alazydogxd.youlai.common.validation.validator.anno;

import com.alazydogxd.youlai.common.validation.validator.FileValidator;
import com.alazydogxd.youlai.common.validation.validator.ImageValidator;

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
 * @date 2022/7/9 15:58
 * @description 是否为图片
 */

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ImageValidator.class})
public @interface IsImage {

    // 当验证不通过时的提示信息
    String message() default "必须为图片文件";

    // 约束注解在验证时所属的组别
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};
}
