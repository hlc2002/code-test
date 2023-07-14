package com.runjing.learn_runjing.erp.postProcessor.servicePostProcessor.impl;

import com.runjing.learn_runjing.erp.general.BaseModel;
import com.runjing.learn_runjing.erp.postProcessor.HandleData;
import com.runjing.learn_runjing.erp.postProcessor.servicePostProcessor.BaseControllerPostProcessor;

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
