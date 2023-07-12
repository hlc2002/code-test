package com.runjing.learn_runjing.erp.client.api;

import com.runjing.learn_runjing.erp.client.proxyConfig.RpcProvider;
import com.runjing.learn_runjing.erp.service.ErpInventoryCoreService;

/**
 * @author forestSpringH
 * @date 2023/7/5
 * @project learn_runjing
 */
@RpcProvider(clientClazz = ErpInventoryCoreService.class)
public class ErpInventoryCoreClient {
}
