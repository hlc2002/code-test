package com.runjing.learn_runjing.serviceLocator;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : huanglinchun
 * @description:
 * @date : Created in 2023/10/18
 * @modified By: huanglinchun
 * @project: learn_runjing
 */
@RestController
public class Client {
    @Resource
    private ServiceType serviceType;

    public void handle(ServiceTypeEnum serviceTypeEnum){
        serviceType.getService(serviceTypeEnum).handle();
    }
}
