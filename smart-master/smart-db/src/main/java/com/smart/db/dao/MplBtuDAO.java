package com.smart.db.dao;

import com.smart.db.model.MplBtu;

import javax.annotation.Resource;

/**
 * MplBtuDAO继承基类
 */
@Resource
public interface MplBtuDAO {

    int deleteByPrimaryKey(Integer id);

    int insert(MplBtu record);

    int insertSelective(MplBtu record);

    MplBtu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MplBtu record);

    int updateByPrimaryKey(MplBtu record);
}