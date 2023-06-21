package com.runjing.learn_runjing.dataSource;

import java.lang.annotation.*;

/**
 * @author forestSpringH
 * @date 2023/6/20
 * @project learn_runjing
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    /*数据源类型*/
    String value() default DataSourceType.READ;
}
