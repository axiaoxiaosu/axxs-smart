package com.smart.oauth2.serve.config;


import com.smart.oauth2.serve.enhancer.UserTokenEnhancer;
import com.smart.oauth2.serve.handler.UserAccessHandler;
import com.smart.oauth2.serve.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @Description: @EnableAuthorizationServer注解开启OAuth2授权服务机制
 * @ProjectName: spring-parent
 * @Package: com.yaomy.security.oauth2.config.AuthorizationServerConfig
 * @Version: 1.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserAccessHandler userAccessHandler;


    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    /**
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里初始化，
     * 你可以把客户端详情信息写死也可以写入内存或者数据库中
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(new JdbcClientDetailsService(dataSource));
    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 用来配置授权（authorization）以及令牌（token)的访问端点和令牌服务（token services）
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //token持久化容器
        tokenServices.setTokenStore(tokenStore());
        //客户端信息
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        //access_token 20分钟 单位秒
        tokenServices.setAccessTokenValiditySeconds(20 * 60);
        //refresh有效时长20分钟 单位秒
        tokenServices.setRefreshTokenValiditySeconds(20 * 60);
        //是否支持refresh_token，默认false
        tokenServices.setSupportRefreshToken(true);
        //是否复用refresh_token,默认为true(如果为false,则每次请求刷新都会删除旧的refresh_token,创建新的refresh_token)
        tokenServices.setReuseRefreshToken(true);

        endpoints.authorizationCodeServices(authorizationCodeServices)
                .authenticationManager(authenticationManager)
                .pathMapping("/login", "/oauth/login")
                //用户账号密码验证
                .userDetailsService(userDetailService)
                //token相关服务
                .tokenServices(tokenServices)
                //控制TokenEndpoint端点请求访问的类型，默认HttpMethod.POST
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);


    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer serverSecurityConfigurer) {
        serverSecurityConfigurer
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");

        serverSecurityConfigurer.authenticationEntryPoint(userAccessHandler)
                .accessDeniedHandler(userAccessHandler)
                .allowFormAuthenticationForClients();
    }


    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new UserTokenEnhancer();
        accessTokenConverter.setSigningKey("202cb962ac59075b964b07152d234b70");
        return accessTokenConverter;
    }
}
