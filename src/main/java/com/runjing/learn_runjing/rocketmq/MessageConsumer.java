package com.runjing.learn_runjing.rocketmq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author forestSpringH
 * @date 2023/6/22
 * @project learn_runjing
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = "TEST_RUNJING_ERP_LEARN_TOPIC", selectorExpression = "tag1",consumerGroup = "test_runjing_erp_learn_consumer")
public class MessageConsumer implements RocketMQListener<Message<String>> {
    @Override
    public void onMessage(Message<String> stringMessage) {
        log.info("accept message={}", JSON.toJSONString(stringMessage));
    }
}
