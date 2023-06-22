package com.runjing.learn_runjing.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author forestSpringH
 * @date 2023/6/23
 * @project learn_runjing
 */
@Configuration
public class RocketMqConfig {

    @Value("${rocketmq.producer.group}")
    private String producerGroup;

    @Value("${rocketmq.name-server}")
    private String nameServer;

    @Bean("RocketMqTemplate")
    public RocketMQTemplate rocketMqTemplate(){
        RocketMQTemplate rocketMqTemplate = new RocketMQTemplate();
        DefaultMQProducer defaultMqProducer = new DefaultMQProducer();
//        DefaultLitePullConsumer defaultLitePullConsumer = new DefaultLitePullConsumer();
//        defaultLitePullConsumer.setConsumerGroup("test_runjing_erp_learn_consumer");
//        defaultLitePullConsumer.setCustomizedTraceTopic("TEST_RUNJING_ERP_LEARN_TOPIC");
        defaultMqProducer.setProducerGroup(producerGroup);
        defaultMqProducer.setNamesrvAddr(nameServer);
        rocketMqTemplate.setProducer(defaultMqProducer);
//        rocketMqTemplate.setConsumer(defaultLitePullConsumer);
        return rocketMqTemplate;
    }
}
