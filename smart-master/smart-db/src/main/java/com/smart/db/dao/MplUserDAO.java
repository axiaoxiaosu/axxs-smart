package com.smart.db.dao;

import com.smart.db.mapper.IMplUserDAO;
import com.smart.db.model.MplUser;
import org.springframework.stereotype.Repository;

/**
 * MplUserDAO继承基类
 */
@Repository
public interface MplUserDAO extends IMplUserDAO {

    int deleteByPrimaryKey(Integer id);

    int insert(MplUser record);

    int insertSelective(MplUser record);

    MplUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MplUser record);

    int updateByPrimaryKey(MplUser record);


}
