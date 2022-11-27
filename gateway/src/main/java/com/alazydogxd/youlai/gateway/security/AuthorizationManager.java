package com.alazydogxd.youlai.gateway.security;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alazydogxd.youlai.common.redisson.service.RedisService;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.alazydogxd.youlai.common.redisson.constant.RedisKeyPrefixes.PATH_ROLE_CODES_KEY;
import static com.alazydogxd.youlai.copy.common.base.constant.RequestHttpHeaders.AUTHORIZATION;
import static com.alazydogxd.youlai.copy.common.base.constant.RequestHttpHeaders.BEARER_TYPE;

/**
 * @author ALazyDogXD
 * @date 2022/6/18 5:10
 * @description 鉴权管理器
 */

@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final String ROLE_ROOT = "ROLE_ROOT";

    private final RedisService redisService;

    public AuthorizationManager(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        // 跨域预检直接通过
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            return Mono.just(new AuthorizationDecision(true));
        }

        String token = request.getHeaders().getFirst(AUTHORIZATION);
        if (StrUtil.isNotBlank(token) && StrUtil.startWithIgnoreCase(token, BEARER_TYPE)) {
            // TODO: app 端没有鉴权直接通过
        } else {
            // 没有 token 鉴权失败
            return Mono.just(new AuthorizationDecision(false));
        }

        // 获取拥有该路径权限的所有角色
        List<String> roleCodes;
        PathMatcher pathMatcher = new AntPathMatcher();
        String path = request.getMethod() + ":" + request.getURI().getPath();
        Map<Object, Object> rolePathMap = redisService.hashGetAll(PATH_ROLE_CODES_KEY);
        Stream<Map.Entry<Object, Object>> stream = rolePathMap.entrySet()
                .stream()
                .filter(e -> pathMatcher.match(e.getKey().toString(), path));
        if (!stream.findAny().isPresent()) {
            // 该路径没有配置权限直接通过
            return Mono.just(new AuthorizationDecision(true));
        } else {
            roleCodes = stream
                    .map(e -> Convert.toList(String.class, e.getValue()))
                    .flatMap(roles -> Arrays.stream(roles.toArray(new String[0])))
                    .collect(Collectors.toList());
        }

        return authentication
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(roleCode -> {
                    // root 用户直接通过
                    if (ROLE_ROOT.equals(roleCode)) {
                        return true;
                    } else {
                        // 当前用户拥有角色有该路径的权限, 通过
                        return roleCodes.contains(roleCode);
                    }
                }).map(AuthorizationDecision::new)
                // 没有权限, 鉴权失败
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
