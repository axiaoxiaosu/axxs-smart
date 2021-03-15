package com.smart.oauth2.serve.handler;

import com.alibaba.fastjson.JSON;
import com.common.result.Msg;
import com.smart.oauth2.serve.util.CorsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Primary
@Component
@Slf4j
public class UserAccessHandler implements AccessDeniedHandler, AuthenticationFailureHandler, AuthenticationSuccessHandler
        , AuthenticationEntryPoint {
    @Value("${smart.page-url}")
    private String pageUrl;

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("------AccessDeniedHandler------");
        HttpServletResponse response = CorsUtil.getResponse(req, res);
        Msg msg = new Msg();
        msg.setCode(300);
        msg.setMsg("用来解决认证过的用户访问无权限资源时的异常");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(msg));
        writer.flush();
        writer.close();
    }

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException {
        System.out.println(String.format("%s---%s---%s", "commence", pageUrl, authException.getMessage()));
        res.sendRedirect(String.format("%s?%s", pageUrl, req.getQueryString()));
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException exception) throws IOException {
        log.error("------onAuthenticationFailure------");
        HttpServletResponse response = CorsUtil.getResponse(req, res);
        response.setContentType("application/json;charset=utf-8");
        Msg msg = new Msg();
        msg.setCode(400);
        msg.setMsg("用户名或密码错误");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(msg));
        writer.flush();
        writer.close();
    }

    /**
     * 验证成功后会跳转到上一次请求的地址
     *
     * @param req
     * @param res
     * @param authentication
     * @throws IOException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException {
        log.info("------onAuthenticationSuccess------");
        HttpServletResponse response = CorsUtil.getResponse(req, res);
        response.setContentType("application/json;charset=utf-8");
        Msg msg = new Msg();
        msg.setCode(200);
        msg.setMsg("用户认证成功处理");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(msg));
        writer.flush();
        writer.close();
    }


}
