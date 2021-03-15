package com.smart.api.config;

import com.alibaba.fastjson.JSON;
import com.common.result.Msg;
import com.smart.api.reponse.CorsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
public class AuthenticationHandler extends CorsResponse implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    @Value("smart.login-success")
    public String loginSuccess;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("------登陆成功------");
//        handleCorsResponse(request, response);
//        response.sendRedirect(loginSuccess);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.info("------登陆失败------");
        handleCorsResponse(request, response);
        PrintWriter writer = response.getWriter();
        Msg msg = new Msg(403, exception.getMessage(), "登陆失败", System.currentTimeMillis());
        writer.write(JSON.toJSONString(msg));
        writer.flush();
        writer.close();

    }
}
