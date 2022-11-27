package com.alazydogxd.youlai.common.web.config;

import com.alazydogxd.youlai.copy.common.base.exception.ServiceException;
import com.alazydogxd.youlai.copy.common.base.resp.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.util.List;

import static com.alazydogxd.youlai.copy.common.base.resp.ResponseStatus.FAIL;

/**
 * @author Mr_W
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseMsg internalErrorHandler(Exception e) {
        LOGGER.error("系统异常", e);
        return ResponseMsg.fail();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = ServiceException.class)
    public ResponseMsg serviceErrorHandler(ServiceException e) {
        LOGGER.error("业务异常", e);
        if (e.getResponse() == null) {
            return ResponseMsg.fail();
        }
        return ResponseMsg.fail(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseMsg paramErrorHandler(MethodArgumentNotValidException e) {
        LOGGER.error("参数异常", e);
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return ResponseMsg.fail(fieldError.getField() + fieldError.getDefaultMessage());
            }
        }
        return ResponseMsg.fail();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseMsg handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        LOGGER.error(e.getParameterName() + "不能为空", e);
        return ResponseMsg.fail(e.getParameterName() + "不能为空");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseMsg handleBindException(BindException e) {
        LOGGER.error("字段验证失败", e);
        if (e.getAllErrors().get(0) instanceof FieldError) {
            FieldError fieldError = (FieldError) e.getAllErrors().get(0);
            // PropertyAccessException
            if (e.getAllErrors().get(0).contains(TypeMismatchException.class)) {
                return ResponseMsg.fail(fieldError.getField() + " 字段类型错误");
            } else {
                return ResponseMsg.fail(fieldError.getField() + fieldError.getDefaultMessage());
            }
        } else  {
            return ResponseMsg.fail(e.getAllErrors().get(0).getDefaultMessage());
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MultipartException.class)
    public ResponseMsg handleMultipartException(MultipartException e) {
        LOGGER.error("文件解析失败", e);
        return ResponseMsg.fail("文件解析失败");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseMsg handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        LOGGER.error("请求体丢失", e);
        return ResponseMsg.fail("请求体丢失");
    }


}
