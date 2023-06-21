package com.runjing.learn_runjing.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author forestSpringH
 * @date 2023/6/21
 * @project learn_runjing
 */
public abstract class InitAndDestroyHandler<T> implements InitializingBean, DisposableBean {
    private Map<String, T> beanMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public void destroy() throws Exception {
    }

    void put(String beanName, T value){
        T put = beanMap.put(beanName, value);
        if (Objects.isNull(put)){
            throw new RuntimeException("初始化备份bean信息异常");
        }
    }

    void remove(String beanName){
        T remove = beanMap.remove(beanName);
        if (Objects.isNull(remove)){
            throw new RuntimeException("移除备份bean信息异常");
        }
    }

    T get(String beanName) {
        return beanMap.get(beanName);
    }
}
