package com.runjing.learn_runjing.dataSource.transaction;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * @author : huanglinchun
 * @description:
 * @date : Created in 2023/10/21
 * @modified By: huanglinchun
 * @project: learn_runjing
 */
@Configuration
public class TransactionSyncConfiguration implements DisposableBean, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!TransactionSynchronizationManager.isSynchronizationActive()){
            TransactionSynchronizationManager.initSynchronization();
        }
        TransactionSynchronizationManager.registerSynchronization(new TransactionalManagerAware());
    }

    @Override
    public void destroy() throws Exception {
        if (!TransactionSynchronizationManager.isSynchronizationActive()){
            return;
        }
        List<TransactionSynchronization> synchronizations = TransactionSynchronizationManager.getSynchronizations();
        if (CollectionUtils.isEmpty(synchronizations)){
            return;
        }
        TransactionSynchronizationManager.clearSynchronization();
    }

//    构造之后，属性填充之前，这时我们的TransactionalManagerAware还是空的会爆出异常
//    @PostConstruct
//    public void init(){
//        TransactionSynchronizationManager.registerSynchronization(transactionalManagerAware);
//    }

}
