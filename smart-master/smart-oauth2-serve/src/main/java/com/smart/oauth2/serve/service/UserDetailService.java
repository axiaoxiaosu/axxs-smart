package com.smart.oauth2.serve.service;

import com.smart.db.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailService implements UserDetailsService {

    @Autowired
    private RedisRealize redisRealize;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        if (username == null || username.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        UserPo userPo = redisRealize.getUserPo(username);
        if (userPo == null) {
            throw new UsernameNotFoundException(username);
        }
        //权限相关
        //new User(username, userPo.getMplUser().getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return User.withUsername(username).password(userPo.getMplUser().getPassword()).authorities(userPo.getMplRole().getRoleName()).build();
    }
}
