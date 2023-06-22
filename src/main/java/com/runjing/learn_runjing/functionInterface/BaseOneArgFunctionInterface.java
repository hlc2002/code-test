package com.runjing.learn_runjing.functionInterface;

/**
 * @author forestSpringH
 * @date 2023/6/22
 * @project learn_runjing
 */
@FunctionalInterface
public interface BaseOneArgFunctionInterface<T,R> {
     T handle(T t,R r);
}
