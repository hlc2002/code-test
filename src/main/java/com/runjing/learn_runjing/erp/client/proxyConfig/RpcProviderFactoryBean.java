package com.runjing.learn_runjing.erp.client.proxyConfig;

import lombok.Data;
import org.springframework.aop.framework.ProxyFactoryBean;

/**
 * @author forestSpringH
 * @date 2023/7/4
 * @project learn_runjing
 */
@Data
public class RpcProviderFactoryBean extends ProxyFactoryBean {
    private String proxyBeanName;
}
