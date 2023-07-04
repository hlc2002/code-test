package com.runjing.learn_runjing.erp.client;

import java.lang.annotation.*;

/**
 * @author forestSpringH
 * @date 2023/7/4
 * @project learn_runjing
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Inherited
@Documented
public @interface RpcProvider {
    Class<?> clientClazz();
}
