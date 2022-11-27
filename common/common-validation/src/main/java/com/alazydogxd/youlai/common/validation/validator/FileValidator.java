package com.alazydogxd.youlai.common.validation.validator;

import cn.hutool.core.util.StrUtil;
import com.alazydogxd.youlai.common.validation.validator.anno.FileSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ALazyDogXD
 * @date 2022/7/9 15:32
 * @description 缩略图验证器
 */

public class FileValidator implements ConstraintValidator<FileSize, MultipartFile> {

    /** 文件大小 */
    private long max;

    @Override
    public void initialize(FileSize constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        //检查文件是否为空
        if (file == null || file.isEmpty()) {
            context.buildConstraintViolationWithTemplate("文件不可为空").addConstraintViolation();
            return false;
        }
        //检查文件大小
        if (file.getSize() > max) {
            context.buildConstraintViolationWithTemplate(StrUtil.format("文件大小不得超过 {} byte", max)).addConstraintViolation();
            return false;
        }
        return true;
    }
}
