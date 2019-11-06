package com.smart.db.dao;

import com.smart.db.mapper.IMplRoleMenuDAO;
import com.smart.db.model.MplRoleMenu;
import org.springframework.stereotype.Repository;

/**
 * MplRoleMenuDAO继承基类
 */
@Repository
public interface MplRoleMenuDAO extends IMplRoleMenuDAO {

    int deleteByPrimaryKey(Integer id);

    int insert(MplRoleMenu record);

    int insertSelective(MplRoleMenu record);

    MplRoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MplRoleMenu record);

    int updateByPrimaryKey(MplRoleMenu record);
}