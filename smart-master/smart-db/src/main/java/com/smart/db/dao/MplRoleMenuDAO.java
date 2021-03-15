package com.smart.db.dao;

import com.smart.db.model.MplRoleMenu;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

/**
 * MplRoleMenuDAO继承基类
 */
@Resource
public interface MplRoleMenuDAO {

    int deleteByPrimaryKey(Integer id);

    int insert(MplRoleMenu record);

    int insertSelective(MplRoleMenu record);

    MplRoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MplRoleMenu record);

    int updateByPrimaryKey(MplRoleMenu record);

    int deleteByMenuId(@Param("menuIds") List<Long> menuIds);
}