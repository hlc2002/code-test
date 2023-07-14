package com.runjing.learn_runjing.erp.postProcessor;

import com.runjing.learn_runjing.spring.SpringHolder;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;

/**
 * @author forestSpringH
 * @date 2023/6/21
 * @project learn_runjing
 */
public class PostProcessorExecute<T> {
    private Class<BasePostProcessor> initPostProcessor;

    public static <T> PostProcessorExecute getInstance(Class<T> initPostProcessor) {
        PostProcessorExecute postProcessor = new PostProcessorExecute();
        postProcessor.initPostProcessor = initPostProcessor;
        return postProcessor;
    }

    public Boolean handleBefore(HandleData<T> handleData){
        List<BasePostProcessor> processorList = SpringHolder.getBeanList(initPostProcessor);
        if (CollectionUtils.isEmpty(processorList)){
            return true;
        }
        processorList.stream().sorted(Comparator.comparing(BasePostProcessor::getPriority))
                .forEach(e -> e.handleBefore(handleData));
        return false;
    }

    public void handleAfter(HandleData<T> handleData) {
        List<BasePostProcessor> processorList = SpringHolder.getBeanList(initPostProcessor);
        if (CollectionUtils.isEmpty(processorList)){
            return;
        }
        processorList.stream().sorted(Comparator.comparing(BasePostProcessor::getPriority, Comparator.reverseOrder()))
                .forEach(e -> e.handleAfter(handleData));
    }
}
