package com.alazydogxd.youlai.sys.boot.service.impl;

import com.alazydogxd.youlai.sys.boot.entity.RoleDO;
import com.alazydogxd.youlai.sys.boot.entity.RolePathDO;
import com.alazydogxd.youlai.sys.boot.mapper.RoleMapper;
import com.alazydogxd.youlai.sys.boot.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ALazyDogXD
 * @date 2022/6/18 8:48
 * @description 角色服务
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleService {
    @Override
    public List<RolePathDO> getRolePathByRoleCodes(List<String> roleCodes) {
        return baseMapper.getRolePath(new QueryWrapper<>()
                .select(
                        "r.id id",
                        "r.code code",
                        "pa.url_perm url_perm")
                .in(!roleCodes.isEmpty(), "r.code", roleCodes)
                .eq("r.deleted", false)
                .eq("r.status", true));
    }

    @Override
    public List<String> getRoleCodesByUserId(int userId) {
        return baseMapper.getRoleCodes(userId, null);
    }
}
