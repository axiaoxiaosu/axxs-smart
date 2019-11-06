package com.smart.db.dao;

import com.smart.db.mapper.IMplMenuDAO;
import com.smart.db.model.MplMenu;
import org.springframework.stereotype.Repository;

@Repository
public interface MplMenuDAO extends IMplMenuDAO {

    int deleteByPrimaryKey(Long id);

    int insert(MplMenu record);

    int insertSelective(MplMenu record);

    MplMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MplMenu record);

    int updateByPrimaryKey(MplMenu record);

}