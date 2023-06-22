package com.runjing.learn_runjing.rocketmq;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

/**
 * @author forestSpringH
 * @date 2023/6/22
 * @project learn_runjing
 */
@Component
@Slf4j
public class MessageProducer {

    @Value("${rocketmq.producer.send-message-timeout}")
    private Integer sendMessageTimeout;

    private static final String TOPIC = "TEST_RUNJING_ERP_LEARN_TOPIC";

    @Qualifier("RocketMQTemplate")
    @Resource
    private RocketMQTemplate rocketMqTemplate;

    public void  sendMessage(Message<String> message) {
        rocketMqTemplate.convertAndSend(TOPIC+":tag1",MessageBuilder.withPayload(message).build());
    }

    public SendResult sendMessageAndGetResult(Message<String> message){
        SendResult sendResult = rocketMqTemplate.syncSend(TOPIC + ":tag1", MessageBuilder.withPayload(message).build());
        log.info("sendResult={}", JSON.toJSONString(sendResult));
        return sendResult;
    }

    public void sendAsyncMsg(Message<String> message) {
        rocketMqTemplate.asyncSend(TOPIC+":tag1", MessageBuilder.withPayload(message).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                // 处理消息发送成功逻辑
            }
            @Override
            public void onException(Throwable throwable) {
                // 处理消息发送异常逻辑
            }
        });
    }

}
