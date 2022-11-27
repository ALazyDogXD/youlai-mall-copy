package com.alazydogxd.youlai.auth.boot.enums;

/**
 * @author ALazyDogXD
 * @date 2022/6/12 22:20
 * @description 认证身份标识
 */

public enum AuthenticationIdentity {

    /** 用户名 */
    USERNAME("username"),

    /** 手机号 */
    MOBILE("mobile"),

    /** 开放式认证系统唯一身份标识 */
    OPENID("openId");

    private final String value;

    AuthenticationIdentity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
