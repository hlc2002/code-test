package com.runjing.learn_runjing.rocketmq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author forestSpringH
 * @date 2023/6/22
 * @project learn_runjing
 */
@Service
@Slf4j
@RocketMQMessageListener(topic = "TEST_RUNJING_ERP_LEARN_TOPIC",consumerGroup = "${rocketmq.producer.group}")
public class MessageConsumer implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("accept message={}", JSON.toJSONString(messageExt.getBody()));
    }
}
