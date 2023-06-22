package com.runjing.learn_runjing.functionInterface;

/**
 * @author forestSpringH
 * @date 2023/6/22
 * @project learn_runjing
 */
@FunctionalInterface
public interface BaseTwoArgFunctionInterface <T,Q,R>{
    T handle(T t,Q q,R r);
}
