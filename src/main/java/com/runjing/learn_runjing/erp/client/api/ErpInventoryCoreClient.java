package com.runjing.learn_runjing.erp.client.api;

import com.runjing.learn_runjing.erp.client.proxyConfig.RpcProvider;
import com.runjing.learn_runjing.erp.domain.ErpInventoryCore;
import com.runjing.learn_runjing.erp.service.ErpInventoryCoreService;
import com.runjing.learn_runjing.general.BaseResponse;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

/**
 * @author forestSpringH
 * @date 2023/7/5
 * @project learn_runjing
 */
@HttpExchange("/erp/")
public interface ErpInventoryCoreClient {
    @GetExchange("/getErpInventoryCoreList")
    BaseResponse<ErpInventoryCore> getErpInventoryCoreList(Long id);
}
