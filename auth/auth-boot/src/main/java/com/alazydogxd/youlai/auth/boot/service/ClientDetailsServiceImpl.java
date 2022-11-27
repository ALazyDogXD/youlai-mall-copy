package com.alazydogxd.youlai.auth.boot.service;

import com.alazydogxd.youlai.sys.api.entity.OAuthClientDetails;
import com.alazydogxd.youlai.sys.api.service.OAuthClientDetailsApiService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import static com.alazydogxd.youlai.auth.boot.enums.PasswordEncoderType.NOOP;

/**
 * @author ALazyDogXD
 * @date 2022/6/11 23:58
 * @description 客户端服务
 */

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @DubboReference
    private OAuthClientDetailsApiService oAuthClientDetailsApiService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails clientDetails = null;
        try {
            OAuthClientDetails oAuthClientDetails = oAuthClientDetailsApiService.getByClientId(clientId);
            clientDetails = new BaseClientDetails(
                    oAuthClientDetails.getClientId(),
                    oAuthClientDetails.getResourceIds(),
                    oAuthClientDetails.getScope(),
                    oAuthClientDetails.getAuthorizedGrantTypes(),
                    oAuthClientDetails.getAuthorities(),
                    oAuthClientDetails.getWebServerRedirectUri()
            );
            clientDetails.setClientSecret(NOOP.getPrefix() + oAuthClientDetails.getClientSecret());
            clientDetails.setAccessTokenValiditySeconds(oAuthClientDetails.getAccessTokenValidity());
            clientDetails.setRefreshTokenValiditySeconds(oAuthClientDetails.getRefreshTokenValidity());
            return clientDetails;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientDetails;
    }
}
