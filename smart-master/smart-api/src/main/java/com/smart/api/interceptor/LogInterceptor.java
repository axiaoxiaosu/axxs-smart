package com.smart.api.interceptor;

import com.alibaba.fastjson.JSON;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author lwq
 * @data 2019/8/13
 */
@Aspect
@Component
@Slf4j
public class LogInterceptor {

    private static final String REQUEST_TIME = "request-time";

    @Pointcut("execution(public * com.smart.controller.*Controller.*(..))")
    public void portal() {
    }

    /**
     * 前置
     */
    @Before("portal()")
    public void beforeLog(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        log.info("【请求 URL】：{}", request.getRequestURL());
        log.info("【请求 IP】：{}", request.getRemoteAddr());
        log.info("【请求类名】：{}，【请求方法名】：{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());

        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("【请求参数】：{}，", JSON.toJSONString(parameterMap));
        Long start = System.currentTimeMillis();
        request.setAttribute(REQUEST_TIME, start);
    }

    @Around("portal()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        log.info("【返回值】：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 后置
     */
    @AfterReturning("portal()")
    public void afterReturning() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        Long start = (Long) request.getAttribute(REQUEST_TIME);
        Long end = System.currentTimeMillis();
        log.info("【请求耗时】：{}毫秒", end - start);

        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        log.info("【浏览器类型】：{}，【操作系统】：{}，【原始User-Agent】：{}", userAgent.getBrowser().toString(),
                userAgent.getOperatingSystem().toString(), header);
    }

}
