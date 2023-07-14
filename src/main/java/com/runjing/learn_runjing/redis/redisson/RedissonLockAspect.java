package com.runjing.learn_runjing.redis.redisson;

import jakarta.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author forestSpringH
 * @date 2023/7/14
 * @project learn_runjing
 */
@Aspect
@Component
@DependsOn({"RedissonLockService"})
public class RedissonLockAspect {

    @Resource
    private RedissonLockService redissonLockService;

    @Pointcut("@annotation(com.runjing.learn_runjing.redis.redisson.RedissonAutoLock)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public void lock(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RedissonAutoLock redissonAutoLock = method.getAnnotation(RedissonAutoLock.class);
        long waitTime = redissonAutoLock.waitTime();
        long expireTime = redissonAutoLock.expireTime();
        long id = redissonAutoLock.id();
        String key = id + method.getName();
        try {
            redissonLockService.lock(key, expireTime);
            try {
                joinPoint.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            } finally {
                redissonLockService.unlock(key);
            }
        } catch (Exception e) {
            Boolean tryLock = redissonLockService.tryLock(key, waitTime, expireTime, TimeUnit.SECONDS);
            if (tryLock) {
                try {
                    joinPoint.proceed();
                } catch (Throwable ex) {
                    throw new RuntimeException(ex);
                } finally {
                    redissonLockService.unlock(key);
                }
            }
        } finally {
            redissonLockService.unlock(key);
        }
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {

    }

    @AfterThrowing("pointcut()")
    public void afterThrowing(JoinPoint joinPoint) {

    }
}
