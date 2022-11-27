package com.alazydogxd.youlai.sys.boot.service.impl;

import com.alazydogxd.youlai.sys.boot.entity.UserDO;
import com.alazydogxd.youlai.sys.boot.entity.UserRoleDO;
import com.alazydogxd.youlai.sys.boot.mapper.UserMapper;
import com.alazydogxd.youlai.sys.boot.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ALazyDogXD
 * @date 2022/6/14 22:11
 * @description 用户服务
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Override
    public UserRoleDO getUserRoleByUsername(String username) {
        return baseMapper.getUserRole(username, new QueryWrapper<>().select(
                        "u.id id",
                        "u.username username",
                        "u.password as password",
                        "u.dept_id dept_id",
                        "u.nickname nickname",
                        "u.avatar avatar",
                        "u.status status",
                        "r.code code")
                .eq("u.deleted", false)
                .eq("u.status", true)
                .eq("r.deleted", false)
                .eq("r.status", true));
    }
}
