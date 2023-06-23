package com.runjing.learn_runjing.utils;


import com.alibaba.fastjson.JSONObject;

/**
 * @author forestSpringH
 * @date 2023/6/23
 * @project learn_runjing
 */
public class JsonTools {
    public static JSONObject jsonToJsonObject(String jsonString) {
        return JSONObject.parseObject(jsonString);
    }

    public static  <T> T jsonToObject(String jsonString,Class<T> clazz){
       return JSONObject.parseObject(jsonString,clazz);
    }

}
