package com.runjing.learn_runjing;

import com.runjing.learn_runjing.erp.service.ErpInventoryCoreService;
import com.runjing.learn_runjing.redis.util.RedisUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
        redisUtil.set("hlc", 20);
        Boolean hlc = redisUtil.setNx("hlc", 10, 60L);
        System.out.println(hlc);
    }
}
