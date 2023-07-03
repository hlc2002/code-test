package com.runjing.learn_runjing.dataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author forestSpringH
 * @date 2023/6/20
 * @project learn_runjing
 */
@Slf4j
public class DynamicDataSourceHolder {

    private static final ThreadLocal<String> DYNAMIC_DATASOURCE_KEY = new ThreadLocal<>();

    public static void setDynamicDataSourceKey(String key){
        log.info("数据源切换为：{}",key);
        DYNAMIC_DATASOURCE_KEY.set(key);
    }

    public static String getDynamicDataSourceKey(){
        String key = DYNAMIC_DATASOURCE_KEY.get();
        return key == null ? DataSourceType.READ: key;
    }

    public static void removeDynamicDataSourceKey(){
        log.info("移除数据源：{}",DYNAMIC_DATASOURCE_KEY.get());
        DYNAMIC_DATASOURCE_KEY.remove();
    }
}
