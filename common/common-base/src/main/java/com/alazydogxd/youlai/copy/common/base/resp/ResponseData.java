package com.alazydogxd.youlai.copy.common.base.resp;

import static com.alazydogxd.youlai.copy.common.base.resp.ResponseStatus.SUCCESS;

/**
 * @author ALazyDogXD
 * @date 2021/9/20 15:53
 * @description 响应
 */

public class ResponseData<T> extends ResponseBase {

    private T data;

    private ResponseData() {
    }

    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> resp = new ResponseData<>();
        resp.code = SUCCESS.getCode();
        resp.data = data;
        return resp;
    }

    public T getData() {
        return data;
    }

    public String getCode() {
        return code;
    }
}
