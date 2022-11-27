package com.alazydogxd.youlai.gateway.config;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import com.alazydogxd.youlai.copy.common.base.resp.ResponseMsg;
import com.alazydogxd.youlai.copy.common.base.resp.ResponseStatus;
import com.alazydogxd.youlai.gateway.security.AuthorizationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import static com.alazydogxd.youlai.copy.common.base.constant.RequestHttpHeaders.AUTHORITIES;
import static com.alazydogxd.youlai.copy.common.base.constant.Securities.AUTHORITY_PREFIX;
import static com.alazydogxd.youlai.copy.common.base.resp.ResponseStatus.ACCESS_UNAUTHORIZED;
import static com.alazydogxd.youlai.copy.common.base.resp.ResponseStatus.TOKEN_INVALID_OR_EXPIRED;

/**
 * @author ALazyDogXD
 * @date 2022/6/18 22:10
 * @description Security 配置
 */

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);

    private final AuthorizationManager authorizationManager;

    public SecurityConfiguration(AuthorizationManager authorizationManager) {
        this.authorizationManager = authorizationManager;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter())
                .publicKey(rsaPublicKey()) // 本地获取公钥
        ;
        http.oauth2ResourceServer().accessDeniedHandler((exchange, denied) -> {
            LOGGER.error("处理未授权", denied);
            return Mono.defer(() -> Mono.just(exchange.getResponse()))
                    .flatMap(response -> writeErrorInfo(response, ACCESS_UNAUTHORIZED));
        }).authenticationEntryPoint((exchange, e) -> {
            LOGGER.error("鉴权失败", e);
            return Mono.defer(() -> Mono.just(exchange.getResponse()))
                    .flatMap(response -> writeErrorInfo(response, TOKEN_INVALID_OR_EXPIRED));
        });
        http.authorizeExchange()
                .pathMatchers("/captcha", "/youlai-auth/oauth/token", "/youlai-sys/user/me").permitAll()
                .pathMatchers("/youlai-shop/webjars/**", "/youlai-shop/doc.html", "/youlai-shop/swagger-resources/**", "/youlai-shop/v2/api-docs").permitAll()
                .anyExchange().access(authorizationManager)
                .and()
                .exceptionHandling()
                .accessDeniedHandler((exchange, denied) -> {
                    LOGGER.error("处理未授权", denied);
                    return Mono.defer(() -> Mono.just(exchange.getResponse()))
                            .flatMap(response -> writeErrorInfo(response, ACCESS_UNAUTHORIZED));
                }) // 处理未授权
                .authenticationEntryPoint((exchange, e) -> {
                    LOGGER.error("处理未认证", e);
                    Mono<Void> mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
                            .flatMap(response -> writeErrorInfo(response, TOKEN_INVALID_OR_EXPIRED));
                    return mono;
                }) //处理未认证
                .and().csrf().disable();
        return http.build();
    }

    private Mono<Void> writeErrorInfo(ServerHttpResponse response, ResponseStatus resultCode) {
        switch (resultCode) {
            case ACCESS_UNAUTHORIZED:
            case TOKEN_INVALID_OR_EXPIRED:
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                break;
            case TOKEN_ACCESS_FORBIDDEN:
                response.setStatusCode(HttpStatus.FORBIDDEN);
                break;
            default:
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                break;
        }
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getHeaders().set("Access-Control-Allow-Origin", "*");
        response.getHeaders().set("Cache-Control", "no-cache");
        String body = JSONUtil.toJsonStr(ResponseMsg.resp(resultCode));
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer))
                .doOnError(error -> DataBufferUtils.release(buffer));
    }

    private Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AUTHORITIES);

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

    private RSAPublicKey rsaPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Resource resource = new ClassPathResource("public.key");
        InputStream is = resource.getInputStream();
        String publicKeyData = IoUtil.read(is).toString();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec((Base64.decode(publicKeyData)));

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

}
