package com.smart.oauth2.serve.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.domain.values.Registration;
import de.codecentric.boot.admin.server.utils.jackson.RegistrationDeserializer;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lwq
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AdminServerProperties adminServerProperties;

    public WebConfig(ApplicationEventPublisher publisher, ServerProperties server,
                     ResourcePatternResolver resourcePatternResolver,
                     AdminServerProperties adminServerProperties) {
        this.adminServerProperties = adminServerProperties;
    }


    /**
     * 参数解析
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthenticationPrincipalArgumentResolver());
    }

    /**
     * cors配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(),
                        HttpMethod.PUT.name(), HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name())
                .allowedOrigins("*")
                .allowedHeaders("content-type")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * 配置静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler(adminServerProperties.getContextPath() + "/**")
                .addResourceLocations("classpath:/META-INF/spring-boot-admin-server-ui/");

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.TEXT_HTML);
        mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        mediaTypes.add(new MediaType("application", "xml"));
        mediaTypes.add(new MediaType("text", "xml"));
        mediaTypes.add(new MediaType("application", "*+xml"));
        mediaTypes.add(MediaType.ALL);
        jackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        ObjectMapper objectMapper = jackson2HttpMessageConverter.getObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Registration.class, ToStringSerializer.instance);
        simpleModule.addDeserializer(Registration.class, new RegistrationDeserializer());
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(0, jackson2HttpMessageConverter);
    }

}
