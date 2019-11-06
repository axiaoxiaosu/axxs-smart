package com.smart.db.mapper;

import com.smart.db.model.Iprecord;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IIprecordDAO {

    List<Iprecord> getReportIp();

    /**
     * 查询该ip今天是否已经记录过了
     * @param ipv4ToLong
     * @return
     */
    List<Integer> selectIpByData(@Param("ip") long ipv4ToLong);
}
