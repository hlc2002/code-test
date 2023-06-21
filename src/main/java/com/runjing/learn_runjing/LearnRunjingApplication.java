package com.runjing.learn_runjing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
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
    }

    @Bean("TransactionTemplate")
    @Scope("singleton")
    public TransactionTemplate getTransactionTemplate(PlatformTransactionManager platformTransactionManager) {
        return new TransactionTemplate(platformTransactionManager);
    }
}
