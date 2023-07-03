package com.runjing.learn_runjing.erp.mapper;

import com.runjing.learn_runjing.dataSource.DataSource;
import com.runjing.learn_runjing.dataSource.DataSourceType;
import com.runjing.learn_runjing.erp.domain.ErpInventoryCore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cursor.Cursor;


import java.util.List;

/**
* @author admin
* @description 针对表【erp_inventory_core(库存核心表)】的数据库操作Mapper
* @createDate 2023-06-17 17:08:21
* @Entity generator.domain.ErpInventoryCore
*/
@Mapper
public interface ErpInventoryCoreMapper{
    @DataSource
    Cursor<ErpInventoryCore> getCursor(@Param("size")Integer size);

    @DataSource
    ErpInventoryCore getErpInventoryCore(@Param("id")Long id);
    @DataSource
    List<ErpInventoryCore> getBatch(@Param("list") List<Long> list);
}




