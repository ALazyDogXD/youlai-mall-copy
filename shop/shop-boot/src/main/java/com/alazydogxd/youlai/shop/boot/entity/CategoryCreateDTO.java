package com.alazydogxd.youlai.shop.boot.entity;

import com.alazydogxd.youlai.common.validation.validator.anno.FileSize;
import com.alazydogxd.youlai.common.validation.validator.anno.IsExist;
import com.alazydogxd.youlai.common.validation.validator.anno.IsImage;
import com.alazydogxd.youlai.copy.common.base.convert.ObjectConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author ALazyDogXD
 * @date 2022/6/22 5:41
 * @description 商品分类入参
 */

@ApiModel("商品分类")
public class CategoryCreateDTO implements ObjectConvert<CategoryDO> {

    @Size(max = 32)
    @NotBlank
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    @NotNull
    @IsExist(type = CategoryDO.class, column = "id", enableSkip = true, skip = "0")
    @ApiModelProperty(value = "P_ID", required = true)
    private Integer parentId;

    @IsImage
    @FileSize
    @ApiModelProperty(value = "缩略图", required = true)
    private MultipartFile thumbnail;

    @NotNull
    @ApiModelProperty(value = "顺序", required = true)
    private Integer sort;

    @NotNull
    @ApiModelProperty(value = "显隐控制", required = true)
    private Boolean visible;

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

    public MultipartFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MultipartFile thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "CategoryCreateDTO{" +
                "name='" + name + '\'' +
                ", parentId=" + parentId +
                ", thumbnail=" + thumbnail +
                ", sort=" + sort +
                ", visible=" + visible +
                '}';
    }
}
