package com.alazydogxd.youlai.gateway.entity;

/**
 * @author ALazyDogXD
 * @date 2022/6/5 19:31
 * @description Captcha
 */

public class CaptchaVO {

    private String uuid;

    private String img;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "CaptchaVO{" +
                "uuid='" + uuid + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
