package com.runjing.learn_runjing;

import com.runjing.learn_runjing.erp.service.ErpInventoryCoreService;
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
    public void testCursor(){
//        erpInventoryCoreService.getErpInventoryCoreList(1);
//        erpInventoryCoreService.getErpInventoryCoreList(1);
        List<Long> ids = new LinkedList<>();
        ids.add(1L);
        ids.add(2L);
        erpInventoryCoreService.getBatchInventoryCoreList(ids);
        erpInventoryCoreService.getBatchInventoryCoreList(ids);
    }

}
