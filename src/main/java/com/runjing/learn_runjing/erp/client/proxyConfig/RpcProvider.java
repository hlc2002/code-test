package com.runjing.learn_runjing.erp.client.proxyConfig;

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
    /*标识的客户端字节码类型*/
    Class<?> clientClazz();
}
