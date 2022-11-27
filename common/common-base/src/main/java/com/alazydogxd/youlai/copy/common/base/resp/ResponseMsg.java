package com.alazydogxd.youlai.copy.common.base.resp;

import com.alazydogxd.youlai.copy.common.base.exception.ServiceException;
import org.springframework.util.StringUtils;

import static com.alazydogxd.youlai.copy.common.base.resp.ResponseStatus.*;

/**
 * @author ALazyDogXD
 * @date 2021/9/20 15:53
 * @description 响应
 */

public class ResponseMsg extends ResponseBase implements Response {

    private final String msg;

    private ResponseMsg(String code, String msg) {
        super.code = code;
        this.msg = msg;
    }

    public static ResponseMsg judge(boolean success) {
        return success ? success() : fail();
    }

    public static ResponseMsg success() {
        return resp(SUCCESS);
    }

    public static ResponseMsg success(String msg) {
        return resp(SUCCESS, msg);
    }

    public static ResponseMsg fail() {
        return resp(FAIL);
    }

    public static ResponseMsg fail(String msg) {
        return resp(USER_ERROR, msg);
    }

    public static ResponseMsg fail(ServiceException e) {
        return new ResponseMsg(e.getCode(), e.getMsg());
    }

    public static ResponseMsg resp(ResponseStatus status) {
        return resp(status, status.getMsg());
    }

    private static ResponseMsg resp(ResponseStatus status, String msg) {
        String code = status.getCode();
        if (StringUtils.isEmpty(msg)) {
            msg = status.getMsg();
        }
        return new ResponseMsg(code, msg);
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getCode() {
        return code;
    }

}
