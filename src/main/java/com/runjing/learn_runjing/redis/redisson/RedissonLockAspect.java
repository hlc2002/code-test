package com.runjing.learn_runjing.redis.redisson;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author forestSpringH
 * @date 2023/7/14
 * @project learn_runjing
 */
@Aspect
@Component
public class RedissonLockAspect {

    @Pointcut("@annotation(com.runjing.learn_runjing.redis.redisson.RedissonAutoLock)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object lock(ProceedingJoinPoint joinPoint){
        return null;
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {

    }

    @AfterThrowing("pointcut()")
    public void afterThrowing(JoinPoint joinPoint) {

    }
}
