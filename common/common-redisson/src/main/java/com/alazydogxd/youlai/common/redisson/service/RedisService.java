package com.alazydogxd.youlai.common.redisson.service;

import java.util.Map;

/**
 * @author ALazyDogXD
 * @date 2022/6/18 6:54
 * @description Redis 服务
 */

@SuppressWarnings("SpellCheckingInspection")
public interface RedisService {

    /**
     * hadd
     *
     * @param key    key
     * @param values values
     */
    void hashAddAll(String key, Map<?, ?> values);

    /**
     * hget
     *
     * @param key key
     * @return values
     */
    Map<Object, Object> hashGetAll(String key);

    /**
     * hget
     *
     * @param key   key
     * @param field field
     * @return values
     */
    Object hashGet(String key, String field);

}
