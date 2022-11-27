package com.alazydogxd.youlai.auth.boot.util;

import com.alazydogxd.youlai.common.web.util.RequestUtil;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import org.apache.logging.log4j.util.Strings;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.alazydogxd.youlai.copy.common.base.constant.RequestHttpHeaders.*;

/**
 * @author ALazyDogXD
 * @date 2022/6/12 23:15
 * @description Auth Request 工具类
 */

public class AuthRequestUtil {

    private AuthRequestUtil() {
    }

    public static String getClientId() {
        // 从请求路径中获取
        String clientId = RequestUtil.getParam(CLIENT_ID);
        if (StrUtil.isNotBlank(clientId)) {
            return clientId;
        }

        // 从请求头获取
        String basic = RequestUtil.getHeader(AUTHORIZATION);
        if (StrUtil.isNotBlank(basic) && basic.startsWith(AUTHENTICATION_SCHEME_BASIC)) {
            basic = basic.replace(AUTHENTICATION_SCHEME_BASIC, Strings.EMPTY).trim();
            String basicPlainText = new String(Base64.getDecoder().decode(basic.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
            // client:secret
            clientId = basicPlainText.split(":")[0];
        }
        return clientId;
    }

}
