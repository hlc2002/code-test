package com.runjing.learn_runjing;

import com.runjing.learn_runjing.erp.service.ErpInventoryCoreService;
import com.runjing.learn_runjing.redis.util.RedisUtil;
import com.runjing.learn_runjing.rocketmq.Message;
import com.runjing.learn_runjing.rocketmq.MessageProducer;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class LearnRunjingApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private ErpInventoryCoreService erpInventoryCoreService;

    @Test
    public void testCursor() {
        List<Long> ids = new LinkedList<>();
        ids.add(1L);
        ids.add(2L);
        erpInventoryCoreService.getBatchInventoryCoreList(ids);
        erpInventoryCoreService.getBatchInventoryCoreList(ids);
    }

    @Resource
    private RedisUtil redisUtil;

    @Test
    public void test() {
        redisUtil.set("t0", 0);
        System.out.println(redisUtil.getTtl("t0"));
        redisUtil.setEx("t1",1,10L);
        System.out.println(redisUtil.getTtl("t1"));
        redisUtil.setNx("t2",2,10L);
        System.out.println(redisUtil.getTtl("t2"));
        redisUtil.leftPush("message","{id:1,optionType:delete}");
        System.out.println(redisUtil.rightPop("message"));
        redisUtil.remove("t0");
    }
}
