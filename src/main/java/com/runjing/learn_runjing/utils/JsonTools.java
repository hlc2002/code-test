package com.runjing.learn_runjing.utils;


import com.alibaba.fastjson.JSONObject;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaNumber;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

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

    public static void main(String[] args) throws IOException {
        Globals globals = JsePlatform.standardGlobals();
        globals.loadfile(new ClassPathResource("lua/test.lua").getFile().getAbsolutePath()).call();
        LuaValue compute = globals.get(LuaValue.valueOf("compute"));
        int call = compute.call(LuaNumber.valueOf(2001), LuaNumber.valueOf(2002)).toint();
        System.out.println(call);
    }

}
