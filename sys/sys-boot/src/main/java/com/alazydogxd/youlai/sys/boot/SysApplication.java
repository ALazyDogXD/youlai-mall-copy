package com.alazydogxd.youlai.sys.boot;

import com.alazydogxd.youlai.common.redisson.service.RedisService;
import com.alazydogxd.youlai.sys.boot.entity.RolePathDO;
import com.alazydogxd.youlai.sys.boot.service.RoleService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.alazydogxd.youlai.common.redisson.constant.RedisKeyPrefixes.PATH_ROLE_CODES_KEY;
import static com.alazydogxd.youlai.copy.common.base.constant.Securities.AUTHORITY_PREFIX;

/**
 * @author ALazyDogXD
 * @date 2022/6/12 1:08
 * @description 系统应用
 */

@SuppressWarnings("SpellCheckingInspection")
@EnableDubbo
@MapperScan("com.alazydogxd.youlai.sys.boot.mapper")
@SpringBootApplication
public class SysApplication implements ApplicationRunner {

    private final RedisService redisService;

    private final RoleService roleService;

    public SysApplication(RedisService redisService, RoleService roleService) {
        this.redisService = redisService;
        this.roleService = roleService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SysApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 初始化角色请求权限
        List<RolePathDO> rolePaths = roleService.getRolePathByRoleCodes(Collections.emptyList());
        Map<String, String> pathRolesMap = rolePaths
                .stream()
                .map(rolePath -> rolePath.getPaths()
                        .stream()
                        .collect(Collectors.toMap(r -> r, r -> AUTHORITY_PREFIX + rolePath.getCode())))
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1 + "," + v2));
        redisService.hashAddAll(PATH_ROLE_CODES_KEY, pathRolesMap);
    }
}
