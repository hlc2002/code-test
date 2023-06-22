package com.runjing.learn_runjing.redis.util;

import com.runjing.learn_runjing.redis.config.RedisConfiguration;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Objects;

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

    public Boolean setNx(String key, Object value, Long expire) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofSeconds(expire));
    }

    public Boolean remove(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    public Boolean exists(String key) {
        return Objects.nonNull(redisTemplate.opsForValue().get(key));
    }

    public Long leftPush(String listKey,Object value) {
        Long size = redisTemplate.opsForList().size(listKey);
        if (Objects.nonNull(size) && Objects.equals(size.intValue(), 0)) {
            return redisTemplate.opsForList().leftPush(listKey, value);
        }else {
            redisTemplate.opsForList().set(listKey, 0L,value);
            return 0L;
        }
    }

    public Object rightPop(String listKey){
        return redisTemplate.opsForList().rightPop(listKey);
    }

}
