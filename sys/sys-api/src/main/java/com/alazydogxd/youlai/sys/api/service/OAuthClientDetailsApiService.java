package com.alazydogxd.youlai.sys.api.service;

import com.alazydogxd.youlai.sys.api.entity.OAuthClientDetails;

/**
 * @author ALazyDogXD
 * @date 2022/6/12 1:15
 * @description oauth 服务
 */

@SuppressWarnings("ALL")
public interface OAuthClientDetailsApiService {

    /**
     * 获取客户端详情
     *
     * @param clientId 客户端 ID
     * @return 客户端详情
     */
    OAuthClientDetails getByClientId(String clientId);

}
