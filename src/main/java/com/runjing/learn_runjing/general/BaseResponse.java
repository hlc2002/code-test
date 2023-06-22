package com.runjing.learn_runjing.general;

import java.util.HashMap;
import java.util.Map;

/**
 * @author forestSpringH
 * @date 2023/6/21
 * @project learn_runjing
 */
public class BaseResponse<T> extends BaseModel{

    private final int SUCCESS = 200;
    private final int FAILURE = 400;
    private final int ERROR = 403;
    private final int REFUSE = 404;
    private int code;
    private String message;
    private T data;
    private Map<String,T> dataMap = new HashMap<String, T>();

    public static <T> BaseResponse<T> success(String message, T data){
        BaseResponse<T> response = new BaseResponse<T>();
        response.code = response.SUCCESS;
        response.message = message;
        response.data = data;
        return response;
    }


    public static <T> BaseResponse<T> failure(String message){
        BaseResponse<T> response = new BaseResponse<T>();
        response.code = response.FAILURE;
        response.message = message;
        return response;
    }

    public static <T> BaseResponse<T> error(String message) {
        BaseResponse<T> response = new BaseResponse<T>();
        response.code = response.ERROR;
        response.message = message;
        return response;
    }

    public static <T> BaseResponse<T> error() {
        BaseResponse<T> response = new BaseResponse<T>();
        response.code = response.ERROR;
        return response;
    }
}
