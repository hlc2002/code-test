package com.runjing.learn_runjing.redis.redisson;

/**
 * @author forestSpringH
 * @date 2023/7/17
 * @project learn_runjing
 */
@FunctionalInterface
public interface LockExecutor <T>{
    T execute();
}
