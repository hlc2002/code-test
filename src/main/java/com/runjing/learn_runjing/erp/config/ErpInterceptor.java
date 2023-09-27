package com.runjing.learn_runjing.erp.config;

import com.hlc.idempotentboot.jar.before.ThreadLocalData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author forestSpringH
 * @date 2023/6/22
 * @project learn_runjing
 */
@Component
@Slf4j
public class ErpInterceptor extends ThreadLocalData  {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(System.currentTimeMillis()+": 请求接收");
        return super.preHandle(request, response, handler);
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }


    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
