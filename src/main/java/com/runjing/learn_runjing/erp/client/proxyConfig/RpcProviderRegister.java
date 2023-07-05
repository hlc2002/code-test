package com.runjing.learn_runjing.erp.client.proxyConfig;

import com.runjing.learn_runjing.spring.SpringHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author forestSpringH
 * @date 2023/7/4
 * @project learn_runjing
 */
@Component
@ConditionalOnBean(SpringHolder.class)
public class RpcProviderRegister implements BeanFactoryPostProcessor , ApplicationListener<ContextRefreshedEvent> {

    private ConfigurableListableBeanFactory configurableListableBeanFactory;
    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
        configurableListableBeanFactory = beanFactory;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        List<ProxyInfo> proxyInfos = scanRpcProvider();
        proxyInfos.forEach(proxyInfo -> {
            RpcProviderFactoryBean rpcProviderFactoryBean = creatProxyBean(proxyInfo.clientClazz);
            rpcProviderFactoryBean.setProxyBeanName(proxyInfo.beanName);
            rpcProviderFactoryBean.setBeanFactory(configurableListableBeanFactory);
            configurableListableBeanFactory.registerSingleton(proxyInfo.clientClazz.getName(), rpcProviderFactoryBean);
        });
    }

    private RpcProviderFactoryBean creatProxyBean(Class interfaceClass){
        RpcProviderFactoryBean rpcProviderFactoryBean = new RpcProviderFactoryBean();
        rpcProviderFactoryBean.setInterfaces(interfaceClass);
        rpcProviderFactoryBean.setProxyTargetClass(false);
        rpcProviderFactoryBean.setInterceptorNames("rpcProviderMethodInterceptor");
        return rpcProviderFactoryBean;
    }

    private List<ProxyInfo> scanRpcProvider() {
        List<ProxyInfo> result = new ArrayList<>();
        SpringHolder.getApplicationContext().getBeansWithAnnotation(RpcProvider.class).forEach((name, instance) ->{
           ProxyInfo proxy = new ProxyInfo();
           proxy.beanName = name;
           RpcProvider rpcProvider = AnnotatedElementUtils.getMergedAnnotation(instance.getClass(),RpcProvider.class);
           proxy.clientClazz = rpcProvider.clientClazz();
           result.add(proxy);
        });
        return result;
    }


    public static class ProxyInfo{
        public Class clientClazz;
        public String beanName;
    }
}
