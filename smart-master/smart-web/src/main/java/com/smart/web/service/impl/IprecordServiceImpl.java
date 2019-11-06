package com.smart.web.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.net.NetUtil;
import com.alibaba.fastjson.JSONObject;
import com.common.util.IpUtil;
import com.smart.db.dao.IprecordDAO;
import com.smart.db.model.Iprecord;
import com.smart.web.service.IprecordService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author lwq
 */
@Slf4j
@Service
public class IprecordServiceImpl implements IprecordService {


    @Autowired
    private IprecordDAO iprecordDAO;

    @Autowired
    private RedisTemplate redisTemplate;


    @Async("smartThread")
    @Override
    public void ipAddress(HttpServletRequest request) {
        String ip = IpUtil.getIpAddress(request);
        JSONObject jsonObject;
        //解析ip归属地
        //ip-api解析
        jsonObject = IpUtil.ipApi(ip);
        if (jsonObject == null) {
            //太平洋ip解析
            jsonObject = IpUtil.tpy(ip);
        }

        if (jsonObject == null) {
            log.error("解析ip归属地失败 time[{}], ip[{}],]", DateTime.now(), ip);
            return;
        }

        //查看该ip今天是否已经记录过
        List<Integer> ipList = iprecordDAO.selectIpByData(NetUtil.ipv4ToLong(ip));
        if (ipList != null && !ipList.isEmpty()) {
            return;
        }

        //添加记录
        Iprecord iprecord = new Iprecord();
        iprecord.setCountry(jsonObject.get("country") == null ? jsonObject.get("addr").toString()
                : jsonObject.get("country").toString());
        iprecord.setFunc("ipAddress");
        iprecord.setTime(DateTime.now());
        iprecord.setCity(jsonObject.get("city") == null ? "" : jsonObject.get("city").toString());
        iprecord.setRegionName(jsonObject.get("regionName") == null ? jsonObject.get("pro").toString()
                : jsonObject.get("regionName").toString());
        iprecord.setIp(NetUtil.ipv4ToLong(ip));
        //存储db
        iprecordDAO.insertSelective(iprecord);

    }


}
