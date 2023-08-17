package com.runjing.learn_runjing.rocketmq;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @author forestSpringH
 * @date 2023/6/22
 * @project learn_runjing
 */

@Data
public class Message<T> implements Serializable {
    private Long taskId;
    private T data;
    private Long sendTime = System.currentTimeMillis();
    private final static long serialVersionUID = 1L;

    public String toJSON() {
        return JSON.toJSONString(this);
    }
}
