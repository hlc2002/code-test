package com.runjing.learn_runjing.dataSource.transaction;

import org.springframework.transaction.support.TransactionSynchronization;


/**
 * @author : huanglinchun
 * @description:
 * @date : Created in 2023/10/21
 * @modified By: huanglinchun
 * @project: learn_runjing
 */
public class TransactionalManagerAware implements TransactionSynchronization {
    @Override
    public void afterCommit() {
        TransactionSynchronization.super.afterCommit();
    }

    @Override
    public void afterCompletion(int status) {
        TransactionSynchronization.super.afterCompletion(status);
        if (status == 0){
            System.out.println("回滚事务成功");
        }
        if (status == 1){
            System.out.println("回滚事务成功");
        }
        if (status == 2){
            System.out.println("回滚事务成功");
        }
    }

    @Override
    public void beforeCommit(boolean readOnly) {
        TransactionSynchronization.super.beforeCommit(readOnly);
        System.out.println("准备提交事务");
    }

    @Override
    public void beforeCompletion() {
        TransactionSynchronization.super.beforeCompletion();
    }
}
