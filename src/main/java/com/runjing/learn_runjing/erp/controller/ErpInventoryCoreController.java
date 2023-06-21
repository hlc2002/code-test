package com.runjing.learn_runjing.erp.controller;

import com.runjing.learn_runjing.erp.service.ErpInventoryCoreService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author forestSpringH
 * @date 2023/6/21
 * @project learn_runjing
 */
@RestController("/rpc/erp/inventoryCore/")
public class ErpInventoryCoreController {
    @Resource
    private ErpInventoryCoreService inventoryCoreService;

}
