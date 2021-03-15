package com.smart.db.dao;

import com.smart.db.model.MplTask;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface MplTaskDAO {

    int deleteByPrimaryKey(Long jobId);

    int insert(MplTask record);

    int insertSelective(MplTask record);

    MplTask selectByPrimaryKey(Long jobId);

    int updateByPrimaryKeySelective(MplTask record);

    int updateByPrimaryKey(MplTask record);

    List<MplTask> selectJobByName(@Param("taskName") String taskName);
}