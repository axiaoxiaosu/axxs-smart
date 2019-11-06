package com.common.interceptors;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lwq
 * @data 2019/7/16
 */
@Slf4j
@Order(-101)
@Component
public class TimeInter implements HandlerInterceptor {

    private String url;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        url = request.getRequestURI();
        request.setAttribute("time", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long timeDifference = System.currentTimeMillis() - (long) request.getAttribute("time");
        if ((timeDifference / 1000) > 5) {
            log.error("请求大于5秒耗时过长url为{},耗时为{}", url, timeDifference);
        }
        log.warn("本次请求耗时{}ms", timeDifference);

    }
}
