package com.alazydogxd.youlai.copy.common.base.convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;

/**
 * @author Mr_W
 * 对象转换 DTO -> DO / DO -> VO
 */
public interface ObjectConvert<T> {

    Logger LOGGER = LoggerFactory.getLogger(ObjectConvert.class);

    /**
     * 对象转换前置处理
     */
    default void beforeConvert() {
    }

    /**
     * 对象转换前置处理
     *
     * @param t 入参对象 t 转 this
     */
    default void beforeConvert(T t) {
    }

    /**
     * 对象转换后置处理
     *
     * @param t 入参对像
     */
    default void afterConvert(T t) {
    }

    /**
     * 实体转换 DTO -> DO
     *
     * @return t
     */
    @SuppressWarnings("unchecked")
    default T convert() {
        T t;
        beforeConvert();
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, t);
        LOGGER.debug("source: {} -> target :{}", this, t);
        afterConvert(t);
        return t;
    }

    /**
     * 实体转换 DO -> VO
     *
     * @param t 入参对象
     * @return this
     */
    default Object convert(T t) {
        beforeConvert(t);
        BeanUtils.copyProperties(t, this);
        LOGGER.debug("source: {} -> target :{}", t, this);
        afterConvert(t);
        return this;
    }

}
