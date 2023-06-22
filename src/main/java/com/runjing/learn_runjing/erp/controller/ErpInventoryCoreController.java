package com.runjing.learn_runjing.erp.controller;

import com.runjing.learn_runjing.erp.domain.ErpInventoryCore;
import com.runjing.learn_runjing.erp.service.ErpInventoryCoreService;
import com.runjing.learn_runjing.general.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author forestSpringH
 * @date 2023/6/21
 * @project learn_runjing
 */
@RestController
@RequestMapping("/rpc/erp/inventoryCore/")
@Api(tags = "Erp库存核心API")
public class ErpInventoryCoreController {
    @Resource
    private ErpInventoryCoreService inventoryCoreService;

    @GetMapping("/list/{size}")
    @ResponseBody
    @ApiOperation(value = "获取库存核心列表",notes = "入参为size单次查询条目数量", response = List.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "单次查询数量", required = true,dataTypeClass = Integer.class)
    })
    public BaseResponse<List<ErpInventoryCore>> getErpInventoryCoreList(@PathVariable("size")Integer size){
        Assert.notNull(size,"参数size为空");
        return BaseResponse.success("",inventoryCoreService.getErpInventoryCoreList(size));
    }

}
