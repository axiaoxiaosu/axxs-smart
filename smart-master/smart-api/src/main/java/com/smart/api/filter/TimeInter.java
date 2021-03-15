package com.smart.api.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lwq
 * @data 2019/7/16
 */
@Slf4j
@Order(-101)
@Component
public class TimeInter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("time", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long timeDifference = System.currentTimeMillis() - (long) request.getAttribute("time");
        if ((timeDifference / 1000) > 5) {
            log.error("请求大于5秒耗时过长url为{},耗时为{}", request.getRequestURI(), timeDifference);
        }
        log.info("本次请求耗时{}ms", timeDifference);
    }
}
