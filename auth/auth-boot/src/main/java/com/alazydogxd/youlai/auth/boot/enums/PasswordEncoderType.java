package com.alazydogxd.youlai.auth.boot.enums;

/**
 * @author ALazyDogXD
 * @date 2022/6/12 1:36
 * @description 密码加密模式
 */

public enum PasswordEncoderType {

    /** BCRYPT加密 */
    BCRYPT("{bcrypt}"),
    /** 无加密明文 */
    NOOP("{noop}");

    private final String prefix;

    PasswordEncoderType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public String toString() {
        return "PasswordEncoderTypeEnum{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
