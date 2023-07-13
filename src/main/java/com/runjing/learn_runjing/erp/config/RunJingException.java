package com.runjing.learn_runjing.erp.config;

import lombok.Data;

/**
 * @author forestSpringH
 * @date 2023/7/8
 * @package runjing_learn.com.runjing.learn_runjing.erp.config
 * @info
 */
@Data
public class RunJingException extends RuntimeException{
    private int exceptionCode;
    private String exceptionMsg;
    private String exceptionAddress;
    public RunJingException(String exceptionMsg){
        super(exceptionMsg);
    }
}