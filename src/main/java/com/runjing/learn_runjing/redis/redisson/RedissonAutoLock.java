package com.runjing.learn_runjing.redis.redisson;

import java.lang.annotation.*;

/**
 * @author forestSpringH
 * @date 2023/7/14
 * @project learn_runjing
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface RedissonAutoLock {
    long  id();
    String lockMethodAndCurrentTime();
    int waitTime() default 3;
    long expireTime() default 3000;
    String description() default "";
    boolean assembleUserInfo() default false;
}
