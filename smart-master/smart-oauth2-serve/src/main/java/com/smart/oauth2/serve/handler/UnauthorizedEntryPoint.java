package com.smart.oauth2.serve.handler;

import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;


public class UnauthorizedEntryPoint extends LoginUrlAuthenticationEntryPoint {
    /**
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public UnauthorizedEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Methods", "GET");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Max-Age", "2592000");
//        response.setContentType("application/json; charset=utf-8");
//        response.setCharacterEncoding("utf-8");
//        super.commence(request, response, authException);
//    }
}
