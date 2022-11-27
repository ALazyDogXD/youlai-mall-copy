package com.alazydogxd.youlai.auth.boot.config;

import com.alazydogxd.youlai.auth.boot.entity.SysUserDetails;
import com.alazydogxd.youlai.auth.boot.granter.CaptchaTokenGranter;
import com.alazydogxd.youlai.auth.boot.service.ClientDetailsServiceImpl;
import com.alazydogxd.youlai.auth.boot.service.UserDetailsServiceImpl;
import com.alazydogxd.youlai.copy.common.base.resp.ResponseMsg;
import com.alibaba.fastjson.JSON;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alazydogxd.youlai.copy.common.base.resp.ResponseStatus.CLIENT_AUTHENTICATION_FAILED;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static org.springframework.http.HttpHeaders.CACHE_CONTROL;

/**
 * @author ALazyDogXD
 * @date 2022/6/7 22:31
 * @description 认证配置
 */

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthorizationServerConfiguration.class);

    private final AuthenticationManager authenticationManager;

    private final ClientDetailsServiceImpl clientDetailsService;

    private final UserDetailsServiceImpl userDetailsService;

    public AuthorizationServerConfiguration(AuthenticationManager authenticationManager,
                                            ClientDetailsServiceImpl clientDetailsService,
                                            UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.clientDetailsService = clientDetailsService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * 自定义认证异常响应数据
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> {
            LOGGER.error("认证失败", e);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            response.setHeader(CACHE_CONTROL, "no-cache");
            ResponseMsg result = ResponseMsg.resp(CLIENT_AUTHENTICATION_FAILED);
            response.getWriter().print(JSON.toJSONString(result));
            response.getWriter().flush();
        };
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 先设置好 authenticationManager, 不然等会调用 endpoints.getTokenGranter() 时就不有密码模式了
        endpoints.authenticationManager(authenticationManager);

        // 先配置好 JwtAccessTokenConverter, 下面的很多地方配置过程中源码会对 AccessTokenConvert 的类型进行判断从而进行初始化配置
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        endpoints.accessTokenConverter(jwtAccessTokenConverter);

        // 先设置好, 下面源码中会用
        endpoints.setClientDetailsService(clientDetailsService);

        // 先设置好, 下面源码中会用
        endpoints.userDetailsService(userDetailsService);

        // 先设置好, 下面源码中会用
        endpoints.tokenEnhancer(getTokenEnhancer(endpoints));

        // 初始化 tokenServices
        endpoints.getDefaultAuthorizationServerTokenServices();

        // 配置授权模式, 为了添加自定义的授权模式, 这里会用到 tokenService, 所以要先配置好 tokenService
        endpoints.tokenGranter(getTokenGranter(endpoints));
    }

    private KeyPair keyPair() {
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
        return factory.getKeyPair("jwt", "123456".toCharArray());
    }

    private CompositeTokenGranter getTokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> tokenGranters = new ArrayList<>(2);

        // 获取默认的 TokenGranter
        TokenGranter tokenGranter = endpoints.getTokenGranter();

        // 自定义验证码模式, 这里有些对象 Spring 无法帮忙注入, 所以只能自己手动注入了
        CaptchaTokenGranter captchaTokenGranter = new CaptchaTokenGranter(
                authenticationManager,
                endpoints.getTokenServices(),
                endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory());

        tokenGranters.add(tokenGranter);
        tokenGranters.add(captchaTokenGranter);
        return new CompositeTokenGranter(tokenGranters);
    }

    private TokenEnhancerChain getTokenEnhancer(AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>(2);

        // 自定义增强内容
        tokenEnhancers.add(getCustomTokenContent());

        tokenEnhancers.add((JwtAccessTokenConverter) endpoints.getAccessTokenConverter());

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
        return tokenEnhancerChain;
    }

    private TokenEnhancer getCustomTokenContent() {
        return (accessToken, authentication) -> {
            Map<String, Object> additionalInfo = new HashMap<>(16);
            Object principal = authentication.getUserAuthentication().getPrincipal();
            if (principal instanceof SysUserDetails) {
                SysUserDetails sysUserDetails = (SysUserDetails) principal;
                additionalInfo.put("userId", sysUserDetails.getUserId());
                additionalInfo.put("username", sysUserDetails.getUsername());
                additionalInfo.put("deptId", sysUserDetails.getDeptId());
                // 认证身份标识(username:用户名；)
                if (StrUtil.isNotBlank(sysUserDetails.getAuthenticationIdentity())) {
                    additionalInfo.put("authenticationIdentity", sysUserDetails.getAuthenticationIdentity());
                }
            } /*else if (principal instanceof MemberUserDetails) {
                MemberUserDetails memberUserDetails = (MemberUserDetails) principal;
                additionalInfo.put("memberId", memberUserDetails.getMemberId());
                additionalInfo.put("username", memberUserDetails.getUsername());
                // 认证身份标识(mobile:手机号；openId:开放式认证系统唯一身份标识)
                if (StrUtil.isNotBlank(memberUserDetails.getAuthenticationIdentity())) {
                    additionalInfo.put("authenticationIdentity", memberUserDetails.getAuthenticationIdentity());
                }
            }*/
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }
}
