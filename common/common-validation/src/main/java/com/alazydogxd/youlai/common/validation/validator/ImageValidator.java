package com.alazydogxd.youlai.common.validation.validator;

import com.alazydogxd.youlai.common.validation.validator.anno.IsImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.util.Objects;

/**
 * @author ALazyDogXD
 * @date 2022/7/9 15:58
 * @description 图片验证器
 */

public class ImageValidator implements ConstraintValidator<IsImage, MultipartFile> {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        //检查是否是图片
        try {
            if (Objects.isNull(file) || Objects.isNull(ImageIO.read(file.getInputStream()))) {
                context.buildConstraintViolationWithTemplate("非图片文件").addConstraintViolation();
                return false;
            }
        } catch (IOException e) {
            LOGGER.warn("图片验证失败", e);
            return false;
        }
        return true;
    }
}
