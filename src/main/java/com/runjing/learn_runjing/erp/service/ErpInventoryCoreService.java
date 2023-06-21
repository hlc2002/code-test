package com.runjing.learn_runjing.erp.service;

import com.runjing.learn_runjing.erp.domain.ErpInventoryCore;

import java.util.List;

/**
* @author admin
* @description 针对表【erp_inventory_core(库存核心表)】的数据库操作Service
* @createDate 2023-06-17 17:08:21
*/
public interface ErpInventoryCoreService {
    List<ErpInventoryCore> getErpInventoryCoreList(Integer size);

    ErpInventoryCore getErpInventoryCoreById(Long id);

    List<ErpInventoryCore> getBatchInventoryCoreList(List<Long> list);
}
