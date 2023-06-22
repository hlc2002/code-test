package com.runjing.learn_runjing.postProcessor.servicePostProcessor.impl;

import com.runjing.learn_runjing.general.BaseModel;
import com.runjing.learn_runjing.postProcessor.HandleData;
import com.runjing.learn_runjing.postProcessor.servicePostProcessor.BaseControllerPostProcessor;

/**
 * @author forestSpringH
 * @date 2023/6/22
 * @project learn_runjing
 */
public class PromisePostProcessorImpl implements BaseControllerPostProcessor {
    @Override
    public boolean handleBefore(HandleData<BaseModel> handle) {
        return BaseControllerPostProcessor.super.handleBefore(handle);
    }

    @Override
    public boolean handleAfter(HandleData<BaseModel> handleData) {
        return BaseControllerPostProcessor.super.handleAfter(handleData);
    }

    @Override
    public int getPriority() {
        return BaseControllerPostProcessor.super.getPriority();
    }
}
