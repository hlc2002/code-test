package com.runjing.learn_runjing.redis.redisson;

import jakarta.annotation.Resource;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author forestSpringH
 * @date 2023/7/14
 * @project learn_runjing
 */
@Component
@ConditionalOnBean(RedissonConfiguration.class)
public class RedissonLock {
    @Resource
    private RedissonClient redisson;

    public boolean isLock(@Nullable String key) {
        return false;
    }

    public void unlock(@Nullable String key) {

    }
}
