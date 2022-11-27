package com.alazydogxd.youlai.sys.boot.mapper;

import com.alazydogxd.youlai.sys.boot.entity.UserDO;
import com.alazydogxd.youlai.sys.boot.entity.UserRoleDO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import static com.baomidou.mybatisplus.core.toolkit.Constants.WRAPPER;

/**
 * @author ALazyDogXD
 * @date 2022/6/14 22:07
 * @description 用户 Mapper
 */

public interface UserMapper extends BaseMapper<UserDO> {

    /**
     * 获取用户角色
     *
     * @param username 用户名
     * @param wrapper  条件
     * @return 用户角色
     */
    UserRoleDO getUserRole(@Param("username") String username, @Param(WRAPPER) Wrapper<?> wrapper);

}
