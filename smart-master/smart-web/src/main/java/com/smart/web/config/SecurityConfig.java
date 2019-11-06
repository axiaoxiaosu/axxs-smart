package com.smart.web.config;

import com.smart.web.filter.SecurityUserDetailsServiceImpl;
import com.smart.web.filter.SmartUsernamePasswordAuthenticationFilter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 注册filter
 *
 * @author lwq
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 处理验证逻辑 进行数据库查询
     */
    @Autowired
    SecurityUserDetailsServiceImpl securityUserDetailsService;

    /**
     * 身份验证对象
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 拦截请求 通过重载，配置如何通过拦截器保护请求
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 允许对静态资源的范围
        http
                // 停用 Spring Security 自带的跨域处理
                .csrf()
                .disable()
                .authorizeRequests()

                // swagger-ui 放行
                .antMatchers(
                        "/**/*.html", "/**/*.css", "/**/*.js", "/swagger-resources/**",
                        "/v2/api-docs/**")
                .permitAll()

                // options放行
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                // 登陆放行
                .antMatchers("/user/login")
                .permitAll()

                // 此外所有请求需要授权
                .anyRequest()
                .authenticated();

        http.addFilterBefore(userFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public SmartUsernamePasswordAuthenticationFilter userFilter() {
        return new SmartUsernamePasswordAuthenticationFilter();
    }

    /**
     * 配置Spring Security的Filter链
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    /**
     * 配置UserDetails服务 需要指定加密校验的方式
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserDetailsService)
                .passwordEncoder(
                        new PasswordEncoder() {
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
                        });
    }
}
