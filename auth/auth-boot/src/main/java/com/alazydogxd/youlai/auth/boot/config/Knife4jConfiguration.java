package com.alazydogxd.youlai.auth.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author ALazyDogXD
 * @date 2022/6/5 23:41
 * @description Swagger 配置
 */

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean
    public Docket defaultApi2() {
        //noinspection SpellCheckingInspection
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("商城练习 API 文档")
                        .description("商城练习")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("mall")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("org.springframework.security.oauth2.provider.endpoint"))
                .paths(PathSelectors.any())
                .build();
    }

}
