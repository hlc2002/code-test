package com.runjing.learn_runjing;

import com.github.xiaoymin.knife4j.core.extend.OpenApiExtendSetting;
import com.github.xiaoymin.knife4j.core.model.MarkdownProperty;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
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
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.LinkedList;
import java.util.List;

/**
 * @author forestSpringH
 */
@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy
@EnableCaching
@EnableSwagger2WebMvc
public class LearnRunjingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnRunjingApplication.class, args);
    }

    @Bean("TransactionTemplate")
    @Scope("singleton")
    public TransactionTemplate getTransactionTemplate(PlatformTransactionManager platformTransactionManager) {
        return new TransactionTemplate(platformTransactionManager);
    }

    @Bean("OpenApi")
    public OpenApiExtensionResolver getOpenApi() {
        MarkdownProperty markdownProperty = new MarkdownProperty();
        markdownProperty.setName("runjing进销存模拟测试");
        markdownProperty.setGroup("com.runjing");
        List<MarkdownProperty> markdownPropertyList = new LinkedList<>();
        markdownPropertyList.add(markdownProperty);
        return new OpenApiExtensionResolver(new OpenApiExtendSetting(), markdownPropertyList);
    }

    @Bean("RocketMQTemplate")
    public RocketMQTemplate getRocketMqTemplate() {
        return new RocketMQTemplate();
    }
}
