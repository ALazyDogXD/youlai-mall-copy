package com.alazydogxd.youlai.gateway.config;

import com.alazydogxd.youlai.gateway.handler.CaptchaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;

/**
 * @author ALazyDogXD
 * @date 2022/6/5 19:23
 * @description 路由配置
 */

@Configuration
public class RouterConfig {

    private static final String PATH_CAPTCHA = "/captcha";

    @Bean
    public RouterFunction<ServerResponse> captchaRouter(CaptchaHandler captchaHandler) {
        final String getCaptcha = "";
        return RouterFunctions.nest(path(PATH_CAPTCHA), RouterFunctions
                .route(GET(getCaptcha), captchaHandler::getCaptcha));
    }

}
