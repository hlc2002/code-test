package com.runjing.learn_runjing.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author forestSpringH
 * @date 2023/6/21
 * @project learn_runjing
 */
@Component
@Lazy(value = false)
@Slf4j
public class SpringHolder implements ApplicationContextAware, DisposableBean, ApplicationEventPublisher, ApplicationListener<ApplicationContextEvent> {

    private static ApplicationContext applicationContext;
    @Override
    public void destroy() throws Exception {
        log.info("容器上下文透传感知接受销毁");
        applicationContext = null;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        SpringHolder.applicationContext = applicationContext;
    }

    @Override
    public void publishEvent(@NonNull Object event) {
        assertApplicationContextNonNull();
        Assert.notNull(event,"入参事件为空");
        applicationContext.publishEvent(event);
    }
    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        assert event != null;
        log.info("容器事件监听者收到事件："+event.toString());
    }
    /*根据接口类型获取实现该接口的全部bean*/
    public static <T> List<T> getBeanList(Class<T> interfaceClass) {
        assertApplicationContextNonNull();
        Assert.notNull(interfaceClass,"入参接口类型为空");
        Map<String, T> beansOfType = applicationContext.getBeansOfType(interfaceClass);
        return new LinkedList<>(beansOfType.values());
    }

    public static <T> T getBean(Class<T> beanClass){
        assertApplicationContextNonNull();
        return applicationContext.getBean(beanClass);
    }

    public static <T> T getBean(String beanName){
        assertApplicationContextNonNull();
        return (T)  applicationContext.getBean(beanName);
    }
    private  static void assertApplicationContextNonNull(){
        if(Objects.isNull(applicationContext)){
            throw new RuntimeException("容器上下文透传感知接受异常");
        }
    }
}
