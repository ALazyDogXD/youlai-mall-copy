package com.alazydogxd.youlai.shop.boot.service.impl;

import com.alazydogxd.youlai.copy.common.base.exception.ServiceException;
import com.alazydogxd.youlai.copy.common.minio.service.MinIoFileService;
import com.alazydogxd.youlai.shop.boot.entity.CategoryCreateDTO;
import com.alazydogxd.youlai.shop.boot.entity.CategoryDO;
import com.alazydogxd.youlai.shop.boot.mapper.CategoryMapper;
import com.alazydogxd.youlai.shop.boot.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.javaws.exceptions.InvalidArgumentException;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.alazydogxd.youlai.copy.common.base.resp.ResponseStatus.USER_UPLOAD_FILE_ERROR;

/**
 * @author ALazyDogXD
 * @date 2022/6/21 7:35
 * @description 商品分类服务
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryDO> implements CategoryService {

    private final MinIoFileService minIoFileService;

    @Value("${youlai-shop.image.category.bucket}")
    private String bucket;

    @Value("${youlai-shop.image.category.path}")
    private String path;

    public CategoryServiceImpl(MinIoFileService minIoFileService) {
        this.minIoFileService = minIoFileService;
    }

    @Override
    public void add(CategoryCreateDTO categoryCreate) {
        try (final InputStream in = categoryCreate.getThumbnail().getInputStream()) {
            CategoryDO category = categoryCreate.convert();
            // 暂时存空串
            save(category.setIconUrl(""));
            String url = minIoFileService.upload(bucket, categoryCreate.getThumbnail().getContentType(), path, categoryCreate.getThumbnail().getOriginalFilename(), in);
            update(new LambdaUpdateWrapper<CategoryDO>().set(CategoryDO::getIconUrl, url).eq(CategoryDO::getId, category.getId()));
        } catch (IOException e) {
            throw new ServiceException(USER_UPLOAD_FILE_ERROR, e);
        } catch (InsufficientDataException | NoSuchAlgorithmException | InvalidKeyException | InternalException | InvalidArgumentException | ErrorResponseException | ServerException | InvalidResponseException | XmlParserException e) {
            throw new ServiceException("文件上传失败", e);
        }
    }
}
