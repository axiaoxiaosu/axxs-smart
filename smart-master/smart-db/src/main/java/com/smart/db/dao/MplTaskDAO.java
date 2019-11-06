package com.smart.db.dao;

import com.smart.db.mapper.IMplTaskDAO;
import com.smart.db.model.MplTask;
import org.springframework.stereotype.Repository;

@Repository
public interface MplTaskDAO extends IMplTaskDAO {

    int deleteByPrimaryKey(Long jobId);

    int insert(MplTask record);

    int insertSelective(MplTask record);

    MplTask selectByPrimaryKey(Long jobId);

    int updateByPrimaryKeySelective(MplTask record);

    int updateByPrimaryKey(MplTask record);
}