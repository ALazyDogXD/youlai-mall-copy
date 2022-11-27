package com.alazydogxd.youlai.copy.common.base.constant;

import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;

/**
 * @author ALazyDogXD
 * @date 2022/6/12 23:28
 * @description 认证相关 Http Header, 相关字段与以下类保持一致
 * @see org.springframework.http.HttpHeaders
 * @see org.springframework.security.web.authentication.www.BasicAuthenticationConverter
 * @see org.springframework.security.oauth2.provider.token.AccessTokenConverter
 * @see org.springframework.security.oauth2.common.OAuth2AccessToken
 */
public interface RequestHttpHeaders {

    String AUTHORIZATION = HttpHeaders.AUTHORIZATION;

    String AUTHORITIES = AccessTokenConverter.AUTHORITIES;

    String AUTHENTICATION_SCHEME_BASIC = BasicAuthenticationConverter.AUTHENTICATION_SCHEME_BASIC;

    String CLIENT_ID = AccessTokenConverter.CLIENT_ID;

    String BEARER_TYPE = OAuth2AccessToken.BEARER_TYPE;

    String TOKEN_PAYLOAD = "payload";

}
