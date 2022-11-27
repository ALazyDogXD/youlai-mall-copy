package com.alazydogxd.youlai.sys.boot.mapper;

import com.alazydogxd.youlai.sys.boot.entity.RoleDO;
import com.alazydogxd.youlai.sys.boot.entity.RolePathDO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import static com.baomidou.mybatisplus.core.toolkit.Constants.WRAPPER;

/**
 * @author ALazyDogXD
 * @date 2022/6/18 9:16
 * @description 角色 Mapper
 */

public interface RoleMapper extends BaseMapper<RoleDO> {
    /**
     * 获取角色路径权限
     *
     * @param wrapper 条件
     * @return 用户角色
     */
    List<RolePathDO> getRolePath(@Param(WRAPPER) Wrapper<?> wrapper);

    /**
     * 获取角色编码
     *
     * @param userId  用户 ID
     * @param wrapper 条件
     * @return 角色编码
     */
    List<String> getRoleCodes(@Param("userId") Integer userId, @Param(WRAPPER) Wrapper<?> wrapper);
}
