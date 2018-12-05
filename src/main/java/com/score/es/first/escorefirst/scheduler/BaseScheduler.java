package com.score.es.first.escorefirst.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class BaseScheduler<T> {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    void setCode(String key, String code) {
        stringRedisTemplate.opsForValue().set(key, code, 60, TimeUnit.SECONDS);
    }

    String getCode(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    Set<String> getKeyList() {
        return stringRedisTemplate.keys("*");
    }
}
