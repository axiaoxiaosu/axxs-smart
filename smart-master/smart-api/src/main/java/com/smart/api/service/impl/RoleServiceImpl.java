package com.smart.api.service.impl;

import com.common.annotationoo.SmartDic;
import com.common.constants.Constants;
import com.common.enums.SmartExceptionEunms;
import com.common.exception.SmartException;
import com.common.util.SmartUtil;
import com.github.pagehelper.PageHelper;
import com.smart.api.cache.RedisRealize;
import com.smart.api.model.MyUserDetail;
import com.smart.api.request.GetRolesRequest;
import com.smart.api.request.SetUserRoleRequest;
import com.smart.api.service.RoleService;
import com.smart.db.dao.MplMenuDAO;
import com.smart.db.dao.MplRoleDAO;
import com.smart.db.dao.MplUserDAO;
import com.smart.db.model.MplMenu;
import com.smart.db.model.MplRole;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

/**
 * @author lwq
 * @data 2019/7/16
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RedisRealize redisRealize;
    @Autowired
    private MplRoleDAO roleDAO;

    @Autowired
    private MplUserDAO mplUserDAO;

    @Autowired
    private MplMenuDAO mplMenuDAO;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public MplRole getRole(String username) {
        MplRole userRole = roleDAO.selectRoleByName(username);
        if (userRole == null) {
            throw new SmartException(Constants.FAILCODE, "没有找到用户");
        }
        return userRole;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addRole(MyUserDetail myUserDetail, String roleName) {
        Integer data = roleDAO.selectRoleByRolName(roleName);
        // 角色状态
        if (data > 0) {
            throw new SmartException(Constants.FAILCODE, "此角色已存在");
        }
        MplRole mplRole = new MplRole();
        mplRole.setCreateTime(new Date());
        mplRole.setRoleName(roleName);
        mplRole.setStatus("0");
        mplRole.setCreateUser(String.valueOf(myUserDetail.getUser().getId()));
        return SmartUtil.isSucceed(roleDAO.insert(mplRole));

    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean setUserRole(MyUserDetail myUserDetail, SetUserRoleRequest setUserRoleRequest) {
        // 没有权限操作
        if (myUserDetail == null) {
            throw new SmartException(SmartExceptionEunms.NOT_PERMISSION);
        }
        roleDAO.selectByPrimaryKey(Long.parseLong(setUserRoleRequest.getRoleid()));
        return false;
    }

    @SmartDic
    @Override
    public List<MplRole> getRoles(GetRolesRequest getRolesRequest) {


        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://127.0.0.1:7775/resource/getRole", String.class);
        System.out.println(responseEntity);

        PageHelper.startPage(getRolesRequest.getPageNum(), getRolesRequest.getPageSize());
        return roleDAO.findRoles(getRolesRequest.getRoleName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteRoles(List<Long> roleIds) {
        //查询角色是否存在
        List<MplRole> role = roleDAO.selectByRoleIds(roleIds);
        if (role == null || role.isEmpty()) {
            throw new SmartException(SmartExceptionEunms.ROLE_NOT_FIND);
        }
        //查询角色是否还存在关联用户
        Integer userNum = mplUserDAO.selectByRoId(roleIds);
        if (userNum != null && userNum > 0) {
            throw new SmartException(SmartExceptionEunms.RORLE_BIND_USER);
        }
        // 同时删除该角色所有的菜单
        mplMenuDAO.deleteByRoId((Long[]) ConvertUtils.convert(roleIds, Long.class));
        return SmartUtil.isSucceed(roleDAO.deleteByRoleIds(roleIds));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateRole(MyUserDetail myUserDetail, Long roleId, String roleName) {
        MplRole role = roleDAO.selectByPrimaryKey(Long.valueOf(roleId));
        if (role == null) {
            throw new SmartException(SmartExceptionEunms.ROLE_NOT_FIND);
        }
        role.setUpdateUser(myUserDetail.getUsername());
        role.setUpdateTime(new Date());
        role.setRoleName(roleName);
        role.setId(roleId);
        return SmartUtil.isSucceed(roleDAO.updateByPrimaryKeySelective(role));
    }

    @Override
    public List<MplMenu> mensByRole(Long id) {
        return mplMenuDAO.mensByRole(id);
    }

    /**
     * 绑定菜单到角色
     *
     * @param ids    资源id
     * @param roleId 角色id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer roleBindMent(String[] ids, String roleId) {
        //先删除在添加 -=-
        mplMenuDAO.deleteByRoId(new Long[]{Long.parseLong(roleId)});
        Integer count = mplMenuDAO.roleBindMent((Long[]) ConvertUtils.convert(ids, Long.class), roleId);
        log.warn("【操作了{}条】", count);
        return count;
    }
}
