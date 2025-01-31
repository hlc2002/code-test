package com.runjing.learn_runjing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;


/**
 * @author forestSpringH
 */
@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy
@EnableCaching
public class LearnRunjingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnRunjingApplication.class, args);
    }

    @Bean("TransactionTemplate")
    @Scope("singleton")
    public TransactionTemplate getTransactionTemplate(PlatformTransactionManager platformTransactionManager) {
        return new TransactionTemplate(platformTransactionManager);
    }

    @Bean("ThreadPool")
    @Scope
    public ThreadPoolTaskExecutor getThreadPool(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int cpuNum = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(cpuNum);
        executor.setMaxPoolSize(cpuNum * 2);
        executor.setQueueCapacity(500);
        executor.setKeepAliveSeconds(120);
        executor.setThreadNamePrefix("runjing-learn-");
        return executor;
    }
}
