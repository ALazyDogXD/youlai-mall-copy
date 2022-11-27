package com.alazydogxd.youlai.common.redisson.service.impl;

import com.alazydogxd.youlai.common.redisson.service.RedisService;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ALazyDogXD
 * @date 2022/6/18 6:54
 * @description Redis 服务
 */

@Service
public class RedisServiceImpl implements RedisService {

    private final RedissonClient redissonClient;

    public RedisServiceImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public void hashAddAll(String key, Map<?, ?> values) {
        RMap<Object, Object> rMap = redissonClient.getMap(key);
        rMap.putAll(values);
    }

    @Override
    public Map<Object, Object> hashGetAll(String key) {
        return redissonClient.getMap(key);
    }

    @Override
    public Object hashGet(String key, String field) {
        return redissonClient.getMap(key).get(field);
    }
}
