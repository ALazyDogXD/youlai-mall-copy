package com.alazydogxd.youlai.copy.common.base.resp;

/**
 * @author ALazyDogXD
 * @date 2021/9/20 15:58
 * @description 响应
 */

public interface Response {

    /**
     * 获取响应码
     *
     * @return 响应码
     */
    String getCode();

    /**
     * 响应消息
     *
     * @return 响应消息
     */
    String getMsg();

}
