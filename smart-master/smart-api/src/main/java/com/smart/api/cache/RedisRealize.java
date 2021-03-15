package com.smart.api.cache;

import com.common.constants.RedisKeyConstants;
import com.smart.api.util.TreeUtil;
import com.smart.db.dao.MplMenuDAO;
import com.smart.db.dao.MplRoleDAO;
import com.smart.db.dao.MplUserDAO;
import com.smart.db.model.MenuData;
import com.smart.db.model.MplRole;
import com.smart.db.model.MplUser;
import com.smart.db.po.UserPo;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lwq
 * @data 2019/8/16
 */
@Component
public class RedisRealize {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private MplUserDAO mplUserDAO;
    @Resource
    private MplRoleDAO mplRoleDAO;
    @Resource
    private MplMenuDAO mplMenuDAO;

    public UserPo getUserPo(String username) {
        HashOperations<String, String, UserPo> hashOperations = redisTemplate.opsForHash();
        boolean hasKey = hashOperations.hasKey(RedisKeyConstants.USERGROUPKEY, username);
        if (false) {
            return hashOperations.get(RedisKeyConstants.USERGROUPKEY, username);
        }
        MplUser user = mplUserDAO.selectByUserName(username);
        if (user != null && !user.getUsername().isEmpty()) {
            UserPo po = new UserPo();
            po.setMplUser(user);
            MplRole mplRole = mplRoleDAO.selectRoleByName(username);
            po.setMplRole(mplRole);
            List<MenuData> mplMenus = mplMenuDAO.selectMplMenuByUserName(username);
            //转换tree结构
            po.setMplMenus(TreeUtil.toTreeUpdateParam2(mplMenus, 0,
                    0L));
            hashOperations.delete(RedisKeyConstants.USERGROUPKEY, username);
            hashOperations.put(RedisKeyConstants.USERGROUPKEY, username, po);
            return po;
        }
        return null;
    }

}
