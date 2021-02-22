package com.wetalk.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    //保存数据
    public void set(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    //读取数据
    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
