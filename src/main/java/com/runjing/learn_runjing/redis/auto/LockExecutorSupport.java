package com.runjing.learn_runjing.redis.auto;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author : spring
 * {@code @description:}
 * {@code @date} : 2024/5/14 14 上午9:57
 * {@code @modified} By: spring
 * {@code @project:} runjing_learn
 */
public abstract class LockExecutorSupport implements LockExecutor {
    private final ThreadPoolTaskExecutor poolTaskExecutor;
    private final RedissonClient redissonClient;

    public LockExecutorSupport(ThreadPoolTaskExecutor poolTaskExecutor, RedissonClient redissonClient) {
        this.poolTaskExecutor = poolTaskExecutor;
        this.redissonClient = redissonClient;
    }

    @Override
    public void execute(Runnable runnable, String lockKey, Long seconds) {
        if (StringUtils.hasLength(lockKey.trim())) {
            RLock lock = redissonClient.getLock(lockKey);
            try {
                if (lock.tryLock(seconds, seconds, TimeUnit.SECONDS)) {
                    List<CompletableFuture<Void>> list = new ArrayList<>();
                    list.add(CompletableFuture.runAsync(runnable, poolTaskExecutor));
                    CompletableFuture<Void> completableFuture = CompletableFuture.allOf(list.toArray(new CompletableFuture[0]));
                    completableFuture.get();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                if (lock.isHeldByCurrentThread() && lock.isLocked()){
                    lock.unlock();
                }
            }
        }
    }
}
