package com.runjing.learn_runjing.redis.auto;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author : spring
 * {@code @description:}
 * {@code @date} : 2024/5/14 14 上午9:54
 * {@code @modified} By: spring
 * {@code @project:} runjing_learn
 */
public interface LockExecutor {
    void execute(Runnable runnable, String lockKey,Long seconds);
    void executeThrowException(Runnable runnable);
    void executeThrowException(Runnable runnable, String lockKey);
    void executeThrowException(Runnable runnable, String lockKey, ThreadPoolTaskExecutor poolTaskExecutor);
}
