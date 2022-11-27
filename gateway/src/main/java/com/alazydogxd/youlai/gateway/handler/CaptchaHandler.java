package com.alazydogxd.youlai.gateway.handler;

import cn.hutool.core.util.IdUtil;
import com.alazydogxd.youlai.copy.common.base.resp.ResponseData;
import com.alazydogxd.youlai.gateway.entity.CaptchaVO;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.awt.*;

/**
 * @author ALazyDogXD
 * @date 2022/6/4 21:23
 * @description 图片验证码处理器
 */

@Component
public class CaptchaHandler {

    @NonNull
    public Mono<ServerResponse> getCaptcha(ServerRequest request) {
        CaptchaVO captcha = new CaptchaVO();
        String uuid = IdUtil.simpleUUID();
        captcha.setUuid(uuid);
        captcha.setImg(getCaptcha().toBase64());
        return ServerResponse.ok().body(Mono.just(ResponseData.success(captcha)), ResponseData.class);
    }

    public Captcha getCaptcha() {
        Captcha captcha = new FixedArithmeticCaptcha(120, 36);
        captcha.setLen(2);
        captcha.setFont(new Font("Verdana", Font.PLAIN, 20));
        return captcha;
    }

    private static class FixedArithmeticCaptcha extends ArithmeticCaptcha {
        public FixedArithmeticCaptcha(int width, int height) {
            super(width, height);
        }

        @Override
        protected char[] alphas() {
            // 生成随机数字和运算符
            int n1 = num(1, 10), n2 = num(1, 10);
            int opt = num(3);

            // 计算结果
            int res = new int[]{n1 + n2, n1 - n2, n1 * n2}[opt];
            // 转换为字符运算符
            char optChar = "+-x".charAt(opt);

            this.setArithmeticString(String.format("%s%c%s=?", n1, optChar, n2));
            this.chars = String.valueOf(res);

            return chars.toCharArray();
        }
    }

}
