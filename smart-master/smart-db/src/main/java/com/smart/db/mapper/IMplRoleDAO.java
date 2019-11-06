package com.smart.db.mapper;

import com.smart.db.model.MplRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author lwq
 */
public interface IMplRoleDAO {

    /**
     * 根据用户名查询用户权限
     *
     * @param username 用户名
     * @return 用户权限
     */
    MplRole selectRoleByName(@Param("username") String username);

    /**
     * 根据角色名称查询该角色是否存在
     */
    Integer selectRoleByRolName(@Param("roleName") String roleName);

    /**
     * 查询所有的角色
     */
    List<MplRole> findRoles(@Param("roleName") String roleName);

    /**
     * 根据id查询角色信息
     * @param roleIds
     * @return
     */
    List<MplRole> selectByRoleIds(@Param("ids") Long[] roleIds);

    /**
     * 根据id删除数据
     * @param roleIds
     * @return
     */
    Integer deleteByRoleIds(@Param("ids")Long[] roleIds);
}
