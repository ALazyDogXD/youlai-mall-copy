package com.alazydogxd.youlai.copy.common.base.exception;

import com.alazydogxd.youlai.copy.common.base.resp.Response;
import com.alazydogxd.youlai.copy.common.base.resp.ResponseMsg;
import com.alazydogxd.youlai.copy.common.base.resp.ResponseStatus;

/**
 * @author ALazyDogXD
 * @date 2021/9/20 16:27
 * @description 服务异常
 */

public class ServiceException extends RuntimeException implements Response {

    private Response response;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(ResponseStatus status) {
        super(status.getMsg());
        this.response = ResponseMsg.resp(status);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Response response, Throwable cause) {
        super(response.getMsg(), cause);
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String getCode() {
        return response.getCode();
    }

    @Override
    public String getMsg() {
        return response.getMsg();
    }

}
