package com.alazydogxd.youlai.shop.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ALazyDogXD
 * @date 2022/6/21 7:22
 * @description 商店应用
 */

@MapperScan("com.alazydogxd.youlai.shop.boot.mapper")
@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

}
