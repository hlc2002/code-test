package com.runjing.learn_runjing.rocketmq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author forestSpringH
 * @date 2023/6/22
 * @project learn_runjing
 */
@Getter
@Setter
public class Message <T> implements Serializable {
    private Long taskId;
    private T data;
    private Long sendTime = System.currentTimeMillis();
    private final static long serialVersionUID = 1L;
}
