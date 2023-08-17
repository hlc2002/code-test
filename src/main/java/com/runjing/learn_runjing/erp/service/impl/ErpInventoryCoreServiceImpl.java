package com.runjing.learn_runjing.erp.service.impl;

import com.runjing.learn_runjing.erp.domain.ErpInventoryCore;
import com.runjing.learn_runjing.erp.general.BaseResponse;
import com.runjing.learn_runjing.erp.mapper.ErpInventoryCoreMapper;
import com.runjing.learn_runjing.erp.service.ErpInventoryCoreService;
import com.runjing.learn_runjing.redis.redisson.RedissonAutoLock;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
* @author admin
* @description 针对表【erp_inventory_core(库存核心表)】的数据库操作Service实现
* @createDate 2023-06-17 17:08:21
*/
@Service
@Slf4j
public class ErpInventoryCoreServiceImpl implements ErpInventoryCoreService {

    @Qualifier("TransactionTemplate")
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Resource
    private ErpInventoryCoreMapper erpInventoryCoreMapper;

    @Override
    public List<ErpInventoryCore> getErpInventoryCoreList(Integer size) {
        List<ErpInventoryCore> result = new ArrayList<>();
        transactionTemplate.execute(status->{
            try{
                log.info("开启流数据查询");
                Cursor<ErpInventoryCore> cursor = erpInventoryCoreMapper.getCursor(size);
                log.info("流查询结束，开启读取流数据");
                cursor.forEach(result::add);
                log.info("流数据读取结束");
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("流查询异常");
            }
            return null;
        });
        return result;
    }

    @Override
    @RedissonAutoLock
    public BaseResponse<ErpInventoryCore> getErpInventoryCoreById(Long id) {
        if (Objects.isNull(id)){
            throw new RuntimeException("id为空");
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return BaseResponse.success("", erpInventoryCoreMapper.getErpInventoryCore(id));
    }

    @Override
    public List<ErpInventoryCore> getBatchInventoryCoreList(List<Long> list) {
        if (CollectionUtils.isEmpty(list)){
            throw new RuntimeException("id列表为空");
        }
        return erpInventoryCoreMapper.getBatch(list);
    }

}




