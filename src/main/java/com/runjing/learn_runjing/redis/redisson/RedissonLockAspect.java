package com.runjing.learn_runjing.redis.redisson;

import jakarta.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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
    public void pointcut(){}

    @Around("pointcut()")
    public Object lock(ProceedingJoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RedissonAutoLock redissonAutoLock = method.getAnnotation(RedissonAutoLock.class);
        long waitTime = redissonAutoLock.waitTime();
        long expireTime = redissonAutoLock.expireTime();
        boolean assembleUserInfo = redissonAutoLock.assembleUserInfo();
        long id = redissonAutoLock.id();


        return redissonAutoLock;
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {

    }

    @AfterThrowing("pointcut()")
    public void afterThrowing(JoinPoint joinPoint) {

    }
}
