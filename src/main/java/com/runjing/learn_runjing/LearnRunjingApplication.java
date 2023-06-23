package com.runjing.learn_runjing;

import com.runjing.learn_runjing.rocketmq.Message;
import com.runjing.learn_runjing.rocketmq.MessageProducer;
import com.runjing.learn_runjing.spring.SpringHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
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
        SpringHolder.getBean(MessageProducer.class).sendMessage(new Message<>());
    }

    @Bean("TransactionTemplate")
    @Scope("singleton")
    public TransactionTemplate getTransactionTemplate(PlatformTransactionManager platformTransactionManager) {
        return new TransactionTemplate(platformTransactionManager);
    }
}
