package com.alazydogxd.youlai.common.web.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ALazyDogXD
 * @date 2022/6/12 23:10
 * @description Request 工具类
 */

public class RequestUtil {

    private RequestUtil() {}

    public static String getParam(String param) {
        return getRequest().getParameter(param);
    }

    public static String getHeader(String param) {
        return getRequest().getHeader(param);
    }

    public static HttpServletRequest getRequest() {
        //noinspection ConstantConditions
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

}
