package com.runjing.learn_runjing.erp.client.proxyConfig;

import com.runjing.learn_runjing.spring.SpringHolder;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;

/**
 * @author forestSpringH
 * @date 2023/7/5
 * @project learn_runjing
 */
//定义远程方法拦截器，获取invoke方法拦截属性代理远程服务接口对象
@Service("rpcProviderMethodInterceptor")
@Slf4j
public class RpcProviderMethodInterceptor implements MethodInterceptor, Advice {

    @Nullable
    @Override
    public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
        RpcProviderFactoryBean rpcProviderFactoryBean = (RpcProviderFactoryBean) SpringHolder.getApplicationContext().getBean("&"+invocation.getMethod().getDeclaringClass().getName());
        Object proxyBean = SpringHolder.getApplicationContext().getBean(rpcProviderFactoryBean.getProxyBeanName());
        Method currentMethod = invocation.getMethod();
        Method proxyMethod = proxyBean.getClass().getMethod(currentMethod.getName(), currentMethod.getParameterTypes());
        return proxyMethod.invoke(proxyBean, invocation.getArguments());
    }
}
