package com.alazydogxd.youlai.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.alazydogxd.youlai.copy.common.base.constant.Securities;
import com.nimbusds.jose.JWSObject;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sun.security.util.SecurityConstants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;

import static com.alazydogxd.youlai.copy.common.base.constant.RequestHttpHeaders.*;

/**
 * @author ALazyDogXD
 * @date 2022/6/19 3:48
 * @description Sercurity 过滤器
 */

@Order(0)
@Component
public class SecurityGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // TODO 过滤黑名单, 用于登出
        ServerHttpRequest request = exchange.getRequest();

        String token = request.getHeaders().getFirst(AUTHORIZATION);
        if (StrUtil.isBlank(token) || !StrUtil.startWithIgnoreCase(token, BEARER_TYPE)) {
            return chain.filter(exchange);
        }

        token = StrUtil.replaceIgnoreCase(token, BEARER_TYPE, Strings.EMPTY);
        String payload;
        try {
            payload = StrUtil.toString(JWSObject.parse(token).getPayload());
        } catch (ParseException e) {
            throw new RuntimeException("token 解析失败", e);
        }

        try {
            request = exchange.getRequest().mutate()
                    .header(TOKEN_PAYLOAD, URLEncoder.encode(payload, "UTF-8"))
                    .build();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("token 解析失败", e);
        }
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }
}
