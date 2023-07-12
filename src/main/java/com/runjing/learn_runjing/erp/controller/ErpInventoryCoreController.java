package com.runjing.learn_runjing.erp.controller;

import cn.soboys.restapispringbootstarter.Result;
import com.runjing.learn_runjing.erp.domain.ErpInventoryCore;
import com.runjing.learn_runjing.erp.service.ErpInventoryCoreService;
import com.runjing.learn_runjing.general.BaseResponse;
import com.runjing.learn_runjing.rocketmq.MessageProducer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author forestSpringH
 * @date 2023/6/21
 * @project learn_runjing
 */
@RestController
@Slf4j
public class ErpInventoryCoreController {
    @Resource
    private ErpInventoryCoreService inventoryCoreService;

    public BaseResponse<ErpInventoryCore> getErpInventoryCoreList(@PathVariable("id")Long id){
        log.info("/rpc/erp/inventoryCore/get/"+id);
        Assert.notNull(id,"参数id为空");
        return BaseResponse.success("",inventoryCoreService.getErpInventoryCoreById(id));
    }

}
