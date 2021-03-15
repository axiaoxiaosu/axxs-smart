package com.smart.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author lwq
 * @data 2019/7/16
 */
@Configuration
public class Swagger2Config {

    @Value("${spring.swagger-enable}")
    private boolean enableSwagger;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 忽略AuthenticationPrincipal注解标记的属性
                .ignoredParameterTypes(AuthenticationPrincipal.class)
                .apiInfo(apiInfo())
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.smart.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("smart接口介绍标题")
                .contact(new Contact("axiaoxiaosu", "https://www.axiaoxiaosu.top", "1306577707@qq.com"))
                .description("smart接口介绍标题")
                .version("1.0")
                .build();
    }
}
