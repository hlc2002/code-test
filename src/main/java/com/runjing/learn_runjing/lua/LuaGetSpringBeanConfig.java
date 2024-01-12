package com.runjing.learn_runjing.lua;

import com.runjing.learn_runjing.erp.service.ErpInventoryCoreService;
import lombok.extern.slf4j.Slf4j;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * @author : spring
 * {@code @description:}
 * {@code @date} : 2024/1/12
 * {@code @modified} By: spring
 * {@code @project:} learn_runjing
 */
@Component
@Slf4j
@DependsOn({"erpInventoryCoreService"})
public class LuaGetSpringBeanConfig {

    @Autowired
    private ApplicationContext applicationContext;

    private Globals globals;

    @PostConstruct
    public void executeLuaScriptWithSpringBean() throws IOException {
        log.info("execute   LuaScriptWithSpringBean  init --------------------------> start");
        // 获取Spring Bean
        ErpInventoryCoreService springBean = (ErpInventoryCoreService) applicationContext.getBean("erpInventoryCoreService");
        log.info("springBean --------------------> get success");
        LuaValue coerce = CoerceJavaToLua.coerce(springBean);
        globals = JsePlatform.standardGlobals();
        globals.set("erpInventoryCoreService", coerce);
        globals.loadfile(new ClassPathResource("lua/test.lua").getFile().getAbsolutePath()).call();
        log.info("globals --------------------> set success");
        log.info("execute   LuaScriptWithSpringBean  init --------------------------> end");
    }

    public void lua(){
        globals.get(LuaValue.valueOf("testSpringBean")).call();
    }

    @PreDestroy
    public void destroy() {
    }
}
