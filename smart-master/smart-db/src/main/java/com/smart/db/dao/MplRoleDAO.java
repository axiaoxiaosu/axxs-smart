package com.smart.db.dao;

import com.smart.db.mapper.IMplRoleDAO;
import com.smart.db.model.MplRole;
import org.springframework.stereotype.Repository;

@Repository
public interface MplRoleDAO extends IMplRoleDAO {

    int deleteByPrimaryKey(Long id);

    int insert(MplRole record);

    int insertSelective(MplRole record);

    MplRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MplRole record);

    int updateByPrimaryKey(MplRole record);


}