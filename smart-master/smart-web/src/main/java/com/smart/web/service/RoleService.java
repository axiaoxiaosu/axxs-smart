package com.smart.web.service;

import com.smart.db.model.MplMenu;
import com.smart.db.model.MplRole;
import com.smart.web.model.MyUserDetail;
import com.smart.web.request.GetRolesRequest;
import com.smart.web.request.SetUserRoleRequest;
import java.util.List;

/**
 * @author lwq
 * @data 2019/7/16
 */
public interface RoleService {

    /**
     * 根据用户名获取其角色
     */
    MplRole getRole(String username);

    /**
     * 添加一个新的角色
     */
    boolean addRole(MyUserDetail myUserDetail, String roleName);

    /**
     * 关联角色和用户
     */
    boolean setUserRole(MyUserDetail myUserDetail, SetUserRoleRequest requestSetUserRole);

    /**
     * 分页获取该有的角色信息
     */
    List<MplRole> getRoles(GetRolesRequest getRolesRequest);

    /**
     * 根据id删除角色
     */
    boolean deleteRoles(Long[] roleId);

    /**
     * 根据id修改角色名称
     */
    boolean updateRole(MyUserDetail myUserDetail, Long roleId, String roleName);

    /**
     * 查询角色所拥有的资源
     *
     * @param id 角色id
     */
    List<MplMenu> mensByRole(Long id);

    /**
     * 绑定菜单到角色
     *
     * @param ids 资源id
     * @param roId 角色id
     */
    Integer roleBindMent(String[] ids, String roId);
}
