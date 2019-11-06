package com.smart.db.dao;

import com.smart.db.mapper.IMplUserRoleDAO;
import com.smart.db.model.MplUserRole;
import org.springframework.stereotype.Repository;

/**
 * MplUserRoleDAO继承基类
 */
@Repository
public interface MplUserRoleDAO extends IMplUserRoleDAO {

    int deleteByPrimaryKey(Integer id);

    int insert(MplUserRole record);

    int insertSelective(MplUserRole record);

    MplUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MplUserRole record);

    int updateByPrimaryKey(MplUserRole record);
}