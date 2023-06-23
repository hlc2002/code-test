package com.runjing.learn_runjing.dataSource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author forestSpringH
 * @date 2023/6/20
 * @project learn_runjing
 */
@Aspect
@Slf4j
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.runjing.learn_runjing.dataSource.DataSource)")
    public void point(){}

    @Around("point()")
    public void setDataSource(ProceedingJoinPoint pointcut) throws Throwable{
        String value = getDefineAnnotation(pointcut).value();
        DynamicDataSourceHolder.setDynamicDataSourceKey(value);
        try{
            pointcut.proceed();
        }finally {
            /*防止内存泄露：这里含有一个threadLocal，你不能忘记移除切换的数据源，或你不能忘记清理备份过来的引用。*/
            DynamicDataSourceHolder.removeDynamicDataSourceKey();
        }
    }

    private DataSource getDefineAnnotation(ProceedingJoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod().getAnnotation(DataSource.class);
    }

}
