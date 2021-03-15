package com.smart.db.dao;

import com.smart.db.model.MplRole;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;
@Resource
public interface MplRoleDAO {

    int deleteByPrimaryKey(Long id);

    int insert(MplRole record);

    int insertSelective(MplRole record);

    MplRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MplRole record);

    int updateByPrimaryKey(MplRole record);


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
     */
    List<MplRole> selectByRoleIds(@Param("ids") List<Long> roleIds);

    /**
     * 根据id删除数据
     */
    Integer deleteByRoleIds(@Param("ids") List<Long> roleIds);


}