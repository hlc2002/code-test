package com.runjing.learn_runjing.dataSource.transaction;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

import java.time.LocalDateTime;

/**
 * @author : huanglinchun
 * @description:
 * @date : Created in 2023/10/22
 * @modified By: huanglinchun
 * @project: learn_runjing
 */

@Component("ERPTransactionManager")
public class ERPTransactionManager extends AbstractPlatformTransactionManager {

    private static final ThreadLocal<Object> currentTransaction = new ThreadLocal<>();
    @Override
    protected Object doGetTransaction() throws TransactionException {
        Object transaction = currentTransaction.get();
        if (transaction == null){
            transaction = new Object();
            currentTransaction.set(transaction);
        }
        return transaction;
    }

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException {
        System.out.println("----------------------------begin transaction--------------------"+ LocalDateTime.now());
    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) throws TransactionException {
        System.out.println("----------------------------commit transaction--------------------"+ LocalDateTime.now());
    }

    @Override
    protected void doRollback(DefaultTransactionStatus status) throws TransactionException {
        System.out.println("----------------------------rollback transaction--------------------"+ LocalDateTime.now());

    }
}
