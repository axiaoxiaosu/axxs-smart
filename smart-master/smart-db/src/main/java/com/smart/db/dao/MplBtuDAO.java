package com.smart.db.dao;

import com.smart.db.mapper.IMplBtuDAO;
import com.smart.db.model.MplBtu;
import org.springframework.stereotype.Repository;

/**
 * MplBtuDAO继承基类
 */
@Repository
public interface MplBtuDAO extends IMplBtuDAO {

    int deleteByPrimaryKey(Integer id);

    int insert(MplBtu record);

    int insertSelective(MplBtu record);

    MplBtu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MplBtu record);

    int updateByPrimaryKey(MplBtu record);
}