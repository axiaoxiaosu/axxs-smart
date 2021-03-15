package com.smart.db.dao;

import com.smart.db.model.MplUserRole;

import javax.annotation.Resource;

/**
 * MplUserRoleDAO继承基类
 */
@Resource
public interface MplUserRoleDAO {

    int deleteByPrimaryKey(Integer id);

    int insert(MplUserRole record);

    int insertSelective(MplUserRole record);

    MplUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MplUserRole record);

    int updateByPrimaryKey(MplUserRole record);
}