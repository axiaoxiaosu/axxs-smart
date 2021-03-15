package com.smart.api.reponse;


import org.apache.logging.log4j.util.Strings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsResponse {
    public static void handleCorsResponse(HttpServletRequest request, HttpServletResponse response) {
        String origin;
        if (request.getRequestURI().equals("/login") && request.getParameter("code") != null && !Strings.isBlank(request.getParameter("code"))) {
            origin = "null";
        } else {
            origin = (request.getHeader("Origin") == null || request.getHeader("Origin").contains("null")) ? "*" : request.getHeader("Origin");
        }
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "2592000");
    }
}
