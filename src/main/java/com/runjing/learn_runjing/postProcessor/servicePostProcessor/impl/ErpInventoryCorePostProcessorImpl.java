package com.runjing.learn_runjing.postProcessor.servicePostProcessor.impl;

import com.runjing.learn_runjing.erp.domain.ErpInventoryCore;
import com.runjing.learn_runjing.postProcessor.HandleData;
import com.runjing.learn_runjing.postProcessor.servicePostProcessor.ErpInventoryCorePostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author forestSpringH
 * @date 2023/6/21
 * @project learn_runjing
 */
@Service
@Slf4j
public class ErpInventoryCorePostProcessorImpl implements ErpInventoryCorePostProcessor {
    @Override
    public boolean handleBefore(HandleData<ErpInventoryCore> handle) {
        return ErpInventoryCorePostProcessor.super.handleBefore(handle);
    }

    @Override
    public boolean handleAfter(HandleData<ErpInventoryCore> handleData) {
        return ErpInventoryCorePostProcessor.super.handleAfter(handleData);
    }

    @Override
    public int getPriority() {
        return ErpInventoryCorePostProcessor.super.getPriority();
    }


}
