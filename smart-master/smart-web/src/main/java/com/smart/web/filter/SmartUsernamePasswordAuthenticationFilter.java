package com.smart.web.filter;

import static com.common.constants.Constants.TOKEN_KEY;

import com.common.constants.Constants;
import com.smart.web.model.MyUserDetail;
import com.smart.web.util.JwtTokenUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author lwq 拦截验证token是否合法
 */
@Slf4j
public class SmartUsernamePasswordAuthenticationFilter extends OncePerRequestFilter {

    /**
     * json web token 在请求头的名字
     */
    @Autowired
    SecurityUserDetailsServiceImpl securityUserDetailsService;

    /**
     * 辅助操作 token 的工具类
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = request;
        // 获取请求头的 token
        String authToken = httpRequest.getHeader(TOKEN_KEY);
        if (authToken != null && !httpRequest.getRequestURI().endsWith(Constants.LOGIN)) {
            String username = JwtTokenUtil.getUserNameFromToken(authToken);
            // 如果上面解析 token 成功并且拿到了 username 并且本次会话的权限还未被写入
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 检查用户带来的 token 是否有效
                MyUserDetail userDetails = this.securityUserDetailsService.loadUserByUsername(username);
                // 若是检查通过
                if (JwtTokenUtil.validateToken(authToken, userDetails)) {
                    // 生成通过认证
                    UsernamePasswordAuthenticationToken token =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                    // 将权限写入本次会话
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
