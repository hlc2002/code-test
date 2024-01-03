package com.runjing.learn_runjing.serviceLocator;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : huanglinchun
 * @description:
 * @date : Created in 2023/10/18
 * @modified By: huanglinchun
 * @project: learn_runjing
 */
@Configuration
public class ServiceLocatorConfig {

    @Bean("ServiceFactory")
    public FactoryBean serviceLocatorFactoryBean(){
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ServiceType.class);
        return factoryBean;
    }
}
