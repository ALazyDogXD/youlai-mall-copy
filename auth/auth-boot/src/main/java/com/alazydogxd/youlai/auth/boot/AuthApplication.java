package com.alazydogxd.youlai.auth.boot;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ALazyDogXD
 * @date 2022/6/5 20:58
 * @description 认证服务
 */

@EnableDubbo
@SpringBootApplication
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
