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
    long  id() default 1L;
    long waitTime() default 3L;
    long expireTime() default 10L;
    String description() default "";
    boolean assembleUserInfo() default false;
}
