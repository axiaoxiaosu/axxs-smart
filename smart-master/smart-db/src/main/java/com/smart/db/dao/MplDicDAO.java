package com.smart.db.dao;

import com.smart.db.model.MplDic;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;
@Resource
public interface MplDicDAO {

    int deleteByPrimaryKey(Long id);

    int insert(MplDic record);

    int insertSelective(MplDic record);

    MplDic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MplDic record);

    int updateByPrimaryKey(MplDic record);

    List<MplDic> selectByTableAndCode(@Param("tableName") String tableName, @Param("code") String code);
}