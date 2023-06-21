package com.runjing.learn_runjing.postProcessor;

/**
 * @author forestSpringH
 * @date 2023/6/21
 * @project learn_runjing
 */
public interface BasePostProcessor<T> {
    default boolean handleBefore(HandleData<T> handle){
        return true;
    }

    default boolean handleAfter(HandleData<T> handleData){
        return true;
    }
    default int getPriority(){
        return 0;
    }

}
