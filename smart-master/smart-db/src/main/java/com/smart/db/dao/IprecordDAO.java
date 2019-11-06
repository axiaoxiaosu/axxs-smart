package com.smart.db.dao;

import com.smart.db.mapper.IIprecordDAO;
import com.smart.db.model.Iprecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IprecordDAO extends IIprecordDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Iprecord record);

    int insertSelective(Iprecord record);

    Iprecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Iprecord record);

    int updateByPrimaryKey(Iprecord record);
}