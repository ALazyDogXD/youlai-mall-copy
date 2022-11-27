package com.alazydogxd.youlai.common.redisson.constant;

/**
 * @author ALazyDogXD
 * @date 2022/6/12 17:36
 * @description Redis Key 前缀常量
 */

public interface RedisKeyPrefixes {

    /** 验证码 */
    String CAPTCHA_KEY_PREFIX = "CAPTCHA:";

    /** 请求路径: 角色编码 */
    String PATH_ROLE_CODES_KEY = "PATH:ROLE:CODES";

}
