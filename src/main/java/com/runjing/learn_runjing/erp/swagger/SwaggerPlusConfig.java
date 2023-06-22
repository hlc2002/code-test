package com.runjing.learn_runjing.erp.swagger;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author forestSpringH
 * @date 2023/6/22
 * @project learn_runjing
 */
@Configuration
@DependsOn(value = {"OpenApi"})
public class SwaggerPlusConfig {

    @Qualifier("OpenApi")
    @Resource
    private OpenApiExtensionResolver openapi;

    @Autowired
    public SwaggerPlusConfig(OpenApiExtensionResolver openapi) {
        this.openapi = openapi;
    }

    @Bean("ApiDoc")
    public Docket apiDoc() {
        String groupName = "1.0";
        return new Docket(DocumentationType.SWAGGER_2).pathMapping("/")
                .select().apis(RequestHandlerSelectors.basePackage("com.runjing.learn_runjing.erp.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("京东酒世界进销存模拟测试接口文档")
                        .description("erp_test接口管理")
                        .contact(new Contact("forestSpringH", "/", "huanglinchun@runjingchina.com"))
                        .version("1.0")
                        .build()
                ).extensions(openapi.buildExtensions(groupName));
    }

}
