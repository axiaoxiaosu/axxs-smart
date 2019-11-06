package com.smart.db.dao;

import com.smart.db.model.MplTaskLog;
import org.springframework.stereotype.Repository;

@Repository
public interface MplTaskLogDAO {

    int deleteByPrimaryKey(Long id);

    int insert(MplTaskLog record);

    int insertSelective(MplTaskLog record);

    MplTaskLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MplTaskLog record);

    int updateByPrimaryKey(MplTaskLog record);
}