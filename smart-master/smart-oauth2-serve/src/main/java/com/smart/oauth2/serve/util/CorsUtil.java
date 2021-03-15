package com.smart.oauth2.serve.util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsUtil {
    public static HttpServletResponse getResponse(ServletRequest req, ServletResponse res) {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String origin = (request.getHeader("Origin") == null || request.getHeader("Origin").contains("null")) ? "*" : request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,1");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "2592000");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        return response;
    }
}
