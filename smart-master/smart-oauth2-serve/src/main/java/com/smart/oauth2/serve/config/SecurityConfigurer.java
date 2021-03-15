package com.smart.oauth2.serve.config;


import com.alibaba.fastjson.JSON;
import com.common.result.Msg;
import com.smart.oauth2.serve.filter.MyCorsFilter;
import com.smart.oauth2.serve.handler.UserAccessHandler;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @Description: 启动基于Spring Security的安全认证
 * @ProjectName: spring-parent
 * @Package: com.yaomy.security.oauth2.config.WebSecurityConfigurer
 * @Date: 2019/7/8 17:43
 * @Version: 1.0
 */
@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserAccessHandler userAccessHandler;


    @Autowired
    private MyCorsFilter myCorsFilter;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }


    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.cors();
        httpSecurity.formLogin().loginProcessingUrl("/oauth/login")
                .successHandler(userAccessHandler)
                .failureHandler(userAccessHandler);

        httpSecurity.logout().clearAuthentication(true).invalidateHttpSession(true).logoutUrl("/oauth/logout").logoutSuccessHandler(new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf8");
                PrintWriter writer = response.getWriter();
                Msg msg = new Msg("退出成功");
                writer.write(JSON.toJSONString(msg));
                writer.flush();
                writer.close();
            }
        });


        httpSecurity.exceptionHandling().authenticationEntryPoint(userAccessHandler);
        httpSecurity.authorizeRequests()
                .antMatchers("/oauth/**", "/resource/**").authenticated()
                .anyRequest().permitAll();
        httpSecurity.addFilterBefore(myCorsFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return DigestUtils.md5Hex(rawPassword.toString());
            }

            @Override
            public boolean matches(CharSequence rawPassword,
                                   String encodedPassword) {
                return encodedPassword
                        .equals(DigestUtils.md5Hex(rawPassword.toString()));
            }
        };
    }
}
