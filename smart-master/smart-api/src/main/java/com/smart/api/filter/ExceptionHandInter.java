package com.smart.api.filter;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.alibaba.fastjson.JSON;
import com.common.exception.ExceptionResult;
import com.common.exception.SmartException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lwq 全局异常处理
 */
@Slf4j
@Order(-100)
@Component
public class ExceptionHandInter implements HandlerExceptionResolver {


    @Value("${smart.excetion.enable}")
    private boolean eacetionEnable;


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o,
                                         Exception e) {
        e.printStackTrace();
        ExceptionResult exceptionResult = new ExceptionResult();
        //网络状态
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");

        if (e instanceof SmartException) {
            try {
                log.error(((SmartException) e).getMsg());
                exceptionResult.setCode(((SmartException) e).getCode())
                        .setMsg(((SmartException) e).getMsg());
                response.getWriter().write(JSON.toJSONString(exceptionResult));
            } catch (IOException e1) {
                e.printStackTrace();
            }
            //security 验证没通过e
        }
   /*     else if (e instanceof AuthenticationException) {
            log.error(e.getMessage());
            try {
                exceptionResult.setCode(-1)
                        .setMsg(e.getMessage());
                response.getWriter().write(JSON.toJSONString(exceptionResult));
            } catch (Exception e1) {

            }
        } */
        else {
            if (eacetionEnable) {
                try {
                    log.error(ExceptionUtil.getSimpleMessage(e));
                    exceptionResult.setCode(-1)
                            .setMsg("未知异常");
                    response.getWriter().write(JSON.toJSONString(exceptionResult));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return new ModelAndView();
    }
}
