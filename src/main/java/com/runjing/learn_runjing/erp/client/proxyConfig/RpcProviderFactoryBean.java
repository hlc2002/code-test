package com.runjing.learn_runjing.erp.client.proxyConfig;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.aop.framework.ProxyFactoryBean;

/**
 * @author forestSpringH
 * @date 2023/7/4
 * @project learn_runjing
 */
//ProxyFactoryBean是AOP的核心代理工厂之一，它具备的属性是代理过程中常用的故直接继承得以填充。
@EqualsAndHashCode(callSuper = true)
@Data
public class RpcProviderFactoryBean extends ProxyFactoryBean {
    private String proxyBeanName;
}
