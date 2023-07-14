package com.runjing.learn_runjing.erp.general;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author forestSpringH
 * @date 2023/6/21
 * @project learn_runjing
 */
@Getter
@Setter
public class BaseResponse<T> extends BaseModel{

    private int code;
    private String message;
    private T data;
    private Map<String,T> dataMap = new HashMap<String, T>();

    public static <T> BaseResponse<T> success(String message, T data){
        BaseResponse<T> response = new BaseResponse<T>();
        response.code = SUCCESS;
        response.message = message;
        response.data = data;
        return response;
    }


    public static <T> BaseResponse<T> failure(String message){
        BaseResponse<T> response = new BaseResponse<T>();
        response.code = FAILURE;
        response.message = message;
        return response;
    }

    public static <T> BaseResponse<T> error(String message) {
        BaseResponse<T> response = new BaseResponse<T>();
        response.code = ERROR;
        response.message = message;
        return response;
    }

    public static <T> BaseResponse<T> error() {
        BaseResponse<T> response = new BaseResponse<T>();
        response.code = ERROR;
        return response;
    }
}
