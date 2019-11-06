package com.smart.web.cache;

import com.common.constants.RedisKeyConstants;
import com.common.redis.RedisUtil;
import com.smart.db.dao.MplMenuDAO;
import com.smart.db.dao.MplRoleDAO;
import com.smart.db.dao.MplUserDAO;
import com.smart.db.model.MplMenu;
import com.smart.db.model.MplRole;
import com.smart.db.model.MplUser;
import com.smart.db.po.UserPo;
import com.smart.web.util.TreeUtil;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lwq
 * @data 2019/8/16
 */
@Component
public class RedisRealize extends RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private MplUserDAO mplUserDAO;
    @Autowired
    private MplRoleDAO mplRoleDAO;
    @Autowired
    private MplMenuDAO mplMenuDAO;

    /**
     * 从redis获取UserPo相关数据
     */
    public UserPo getUserPo(String username) {
        HashOperations<String, String, UserPo> hashOperations = redisTemplate.opsForHash();
        boolean haskey = hashOperations.hasKey(RedisKeyConstants.USERGROUPKEY, username);
        if (false) {
            return hashOperations.get(RedisKeyConstants.USERGROUPKEY, username);
        }
        //查詢用户数据并放入缓存
        //1.用户数据 2.用户权限 3.用户资源
        MplUser user = mplUserDAO.selectByUserName(username);
        if (user != null && !user.getUsername().isEmpty()) {
            UserPo po = new UserPo();
            po.setMplUser(user);
            MplRole mplRole = mplRoleDAO.selectRoleByName(username);
            po.setMplRole(mplRole);
            List<MplMenu> mplMenus = mplMenuDAO.selectMplMenuByUserName(username);
            po.setMplMenus(TreeUtil.toTreeUpdateParma(mplMenus, 0,
                    0L));
            //先刪除缓存
            hashOperations.delete(RedisKeyConstants.USERGROUPKEY, username);
            hashOperations.put(RedisKeyConstants.USERGROUPKEY, username, po);
            return po;
        }
        return null;
    }

}
