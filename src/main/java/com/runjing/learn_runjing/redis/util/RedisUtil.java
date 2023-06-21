package com.runjing.learn_runjing.redis.util;

import com.runjing.learn_runjing.redis.config.RedisConfiguration;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author forestSpringH
 * @date 2023/6/21
 * @project learn_runjing
 */
@Component
@ConditionalOnBean(RedisConfiguration.class)
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setNx(String key, Object value,Long expire){
        redisTemplate.multi();
        redisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofSeconds(expire));
        redisTemplate.exec();
    }

}
