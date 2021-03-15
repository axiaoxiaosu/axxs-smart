package com.smart.db.dao;

import com.smart.db.model.Iprecord;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface IprecordDAO {

    int deleteByPrimaryKey(Long id);

    int insert(Iprecord record);

    int insertSelective(Iprecord record);

    Iprecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Iprecord record);

    int updateByPrimaryKey(Iprecord record);

    List<Iprecord> getReportIp();

    /**
     * 查询该ip今天是否已经记录过了
     */
    List<Integer> selectIpByData(@Param("ip") long ipv4ToLong);
}