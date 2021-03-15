package com.smart.oauth2.serve.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyCorsFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String origin = (request.getHeader("Origin") == null || request.getHeader("Origin").contains("null")) ? "*" : request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "2592000");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        filterChain.doFilter(request, response);
    }
}
