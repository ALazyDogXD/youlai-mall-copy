package com.alazydogxd.youlai.sys.boot.dubbo;

import com.alazydogxd.youlai.sys.api.entity.OAuthClientDetails;
import com.alazydogxd.youlai.sys.api.service.OAuthClientDetailsApiService;
import com.alazydogxd.youlai.sys.boot.service.OAuthClientDetailsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;

/**
 * @author ALazyDogXD
 * @date 2022/6/12 3:42
 * @description OAuth 客户端详情 Api
 */

@SuppressWarnings("ALL")
@DubboService
public class OAuthClientDetailsApiServiceImpl implements OAuthClientDetailsApiService {

    private final OAuthClientDetailsService oAuthClientDetailsService;

    public OAuthClientDetailsApiServiceImpl(OAuthClientDetailsService oAuthClientDetailsService) {
        this.oAuthClientDetailsService = oAuthClientDetailsService;
    }

    @Override
    public OAuthClientDetails getByClientId(String clientId) {
        OAuthClientDetails clientDetails = new OAuthClientDetails();
        BeanUtils.copyProperties(oAuthClientDetailsService.getById(clientId), clientDetails);
        return clientDetails;
    }
}
