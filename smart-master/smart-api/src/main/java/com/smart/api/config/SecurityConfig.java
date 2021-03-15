package com.smart.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;


/**
 * 注册filter
 *
 * @author lwq
 */
@Configuration
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    OAuth2ProtectedResourceDetails resourceDetails;

    @Autowired
    ResourceServerProperties properties;

    @Autowired
    AuthenticationHandler authenticationHandler;

    @Qualifier(value = "oauth2ClientContext")
    @Autowired
    private OAuth2ClientContext oAuth2ClientContext;


    /**
     * 拦截请求 通过重载，配置如何通过拦截器保护请求
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //swagger放行
                .antMatchers("/swagger-ui.html"
                        , "/webjars/**"
                        , "/v2/**"
                        , "/swagger-resources/**").permitAll()
                .antMatchers("/", "/login", "/error**", "/management/**", "/admin-serve/**", "/api-actuator/**").permitAll()
                .anyRequest().authenticated();
        http.csrf().disable();
        http.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }


    private Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter smartApiFilter = new OAuth2ClientAuthenticationProcessingFilter(
                "/api/login");
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, oAuth2ClientContext);
        AuthorizationCodeAccessTokenProvider authCodeProvider = new AuthorizationCodeAccessTokenProvider();
        restTemplate.setAccessTokenProvider(authCodeProvider);
        smartApiFilter.setAuthenticationSuccessHandler(authenticationHandler);
        smartApiFilter.setAuthenticationFailureHandler(authenticationHandler);
        smartApiFilter.setRestTemplate(restTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(properties.getUserInfoUri(),
                properties.getClientId());
        tokenServices.setRestTemplate(restTemplate);
        smartApiFilter.setTokenServices(tokenServices);
        return smartApiFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
