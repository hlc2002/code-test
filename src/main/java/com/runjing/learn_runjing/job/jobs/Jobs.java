package com.runjing.learn_runjing.job.jobs;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @author : spring
 * {@code @description:}
 * {@code @date} : 2024/3/26
 * {@code @modified} By: spring
 * {@code @project:} learn_runjing
 */
@Component
@Slf4j
@DependsOn({"xxlJobExecutor"})
public class Jobs {
    @XxlJob("test")
    public void test() {
        log.info("test -----------> 执行任务测试结束");
    }
}
