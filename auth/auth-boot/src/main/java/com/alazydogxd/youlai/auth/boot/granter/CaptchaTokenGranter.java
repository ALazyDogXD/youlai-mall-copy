package com.alazydogxd.youlai.auth.boot.granter;

import com.alazydogxd.youlai.copy.common.base.exception.ServiceException;
import com.alazydogxd.youlai.copy.common.base.resp.ResponseMsg;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.alazydogxd.youlai.copy.common.base.resp.ResponseStatus.BLAND_CAPTCHA;

/**
 * @author ALazyDogXD
 * @date 2022/6/7 20:35
 * @description 验证码授权
 */

public class CaptchaTokenGranter extends ResourceOwnerPasswordTokenGranter {

    private static final String GRANT_TYPE = "captcha";

    public CaptchaTokenGranter(AuthenticationManager authenticationManager,
                               AuthorizationServerTokenServices tokenServices,
                               ClientDetailsService clientDetailsService,
                               OAuth2RequestFactory requestFactory) {
        super(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());

        String captcha = parameters.get("code");
        String uuid = parameters.get("uuid");
        if (StrUtil.isBlank(captcha)) {
            throw new ServiceException(BLAND_CAPTCHA);
        }

        // RedisKeyPrefixes 在 common-redisson 模块下
//        String captchaKey = RedisKeyPrefixes.CAPTCHA_KEY_PREFIX + uuid;

        // 验证码校验
//        String correctValidateCode = redisTemplate.opsForValue().get(captchaKey);
//        Assert.isTrue(StrUtil.isNotBlank(correctValidateCode),"验证码已过期");
//        Assert.isTrue(validateCode.equals(correctValidateCode),"验证码不正确");

        // 验证码验证通过, 删除 Redis 的验证码
//        redisTemplate.delete(captchaKey);

        // 移除后续无用参数
        parameters.remove("code");
        parameters.remove("uuid");
        // 密码验证
        return super.getOAuth2Authentication(client, tokenRequest);
    }
}
