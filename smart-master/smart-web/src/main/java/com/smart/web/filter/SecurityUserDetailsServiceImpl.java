package com.smart.web.filter;

import com.common.constants.Constants;
import com.smart.db.po.UserPo;
import com.smart.web.cache.RedisRealize;
import com.smart.web.model.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lwq
 */
@Service
public class SecurityUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RedisRealize redisCache;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public MyUserDetail loadUserByUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new UsernameNotFoundException(Constants.USERNAMENOTBEBLANK);
        }

        UserPo userPo = redisCache.getUserPo(username);
        if (userPo != null) {
            return new MyUserDetail(userPo);
        }

        throw new UsernameNotFoundException(Constants.NOGFINDUSER);
    }
}
