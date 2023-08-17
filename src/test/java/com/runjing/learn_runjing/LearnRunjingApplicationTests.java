package com.runjing.learn_runjing;

import com.runjing.learn_runjing.erp.controller.ErpInventoryCoreController;
import com.runjing.learn_runjing.erp.domain.ErpInventoryCore;
import com.runjing.learn_runjing.erp.general.BaseResponse;
import com.runjing.learn_runjing.erp.service.ErpInventoryCoreService;
import com.runjing.learn_runjing.redis.util.RedisUtil;
import com.runjing.learn_runjing.utils.AddressAndCodeFiltration;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LearnRunjingApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private ErpInventoryCoreService erpInventoryCoreService;

    @Resource
    private ErpInventoryCoreController erpInventoryCoreController;

    @Resource
    @Qualifier("ThreadPool")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    public void testLock(){
        Long id = 1L;
        threadPoolTaskExecutor.execute(() -> {
            BaseResponse<ErpInventoryCore> erpInventoryCore = erpInventoryCoreController.getErpInventoryCore(id);
            System.out.println(erpInventoryCore);
        });
        BaseResponse<ErpInventoryCore> erpInventoryCore = erpInventoryCoreController.getErpInventoryCore(id);
        System.out.println(erpInventoryCore);
    }

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

    @Test
    public void testGeo(){
        redisUtil.setGeo(12.0,12.0,"我","erp");
        redisUtil.setGeo(22.0,22.0,"米日","erp");
        List<Point> geoPoint = redisUtil.getGeoPoint("erp", "我");
        geoPoint.forEach(System.out::println);
        Map<String, Point> neighborhoodMap = redisUtil.getNeighborhoodMap("erp", 12.0, 12.0, 10000.0);
        System.out.println(neighborhoodMap);
        redisUtil.removeGeo("erp");
    }

    @Test
    public void testGetResultString() {
        // 测试输入包含市、区和京东酒世界的字符串
        assertEquals("朝阳区BJS000001京东酒世界", AddressAndCodeFiltration.getResultString("北京市朝阳区BJS000001京东酒世界"));

        // 测试输入包含市、县和京东酒世界的字符串
        assertEquals("朝阳县BJS000001京东酒世界", AddressAndCodeFiltration.getResultString("北京市朝阳县BJS000001京东酒世界"));

        // 测试输入包含市和京东酒世界的字符串，但不包含区或县
        assertEquals("朝阳区BJS000001京东酒世界", AddressAndCodeFiltration.getResultString("北京市朝阳区BJS000001京东酒世界"));

        // 测试输入包含州和京东酒世界的字符串
        assertEquals("BJS000001京东酒世界", AddressAndCodeFiltration.getResultString("北京州BJS000001京东酒世界"));

        // 测试输入仅含京东酒世界的字符串
        assertEquals("BJS000001", AddressAndCodeFiltration.getResultString("BJS000001京东酒世界"));

        // 测试输入只有空格的字符串
        try {
            AddressAndCodeFiltration.getResultString("   ");
        } catch (RuntimeException e) {
            System.out.println("入参workHouseNameAndCode不符合处理格式");
        }

        // 测试输入一串无意义的字符串
        try {
            AddressAndCodeFiltration.getResultString("ssssss");
        } catch (RuntimeException e) {
            System.out.println("入参workHouseNameAndCode无意义字符串");
        }
    }

}
