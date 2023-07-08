package com.runjing.learn_runjing.erp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author forestSpringH
 * @date 2023/6/22
 * @project learn_runjing
 */
@RestControllerAdvice
@Slf4j
public class ErpControllerAdvice {

    @ExceptionHandler(RunJingException.class)
    public void handleException(RunJingException e) {
        log.error(e.getMessage());
    }
}