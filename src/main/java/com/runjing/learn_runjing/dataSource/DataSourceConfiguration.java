package com.runjing.learn_runjing.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author forestSpringH
 * @date 2023/6/20
 * @project learn_runjing
 */
@Configuration
@ConditionalOnClass(DruidDataSource.class)
@ConditionalOnProperty(
        name = "spring.datasource.druid.write",
        matchIfMissing = true
)
public class DataSourceConfiguration {
    @Bean(name= DataSourceType.WRITE)
    @Qualifier(DataSourceType.WRITE)
    @ConfigurationProperties("spring.datasource.druid.write")
    public DruidDataSource wirteDruidDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name= DataSourceType.READ)
    @Qualifier(DataSourceType.READ)
    @ConfigurationProperties("spring.datasource.druid.read")
    public DruidDataSource readDruidDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource()
    {
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DataSourceType.WRITE,wirteDruidDataSource());
        dataSourceMap.put(DataSourceType.READ,readDruidDataSource());
        //设置动态数据源
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(wirteDruidDataSource());
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }
}
