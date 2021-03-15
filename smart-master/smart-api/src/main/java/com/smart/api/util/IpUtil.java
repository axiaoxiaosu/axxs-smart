package com.smart.api.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * @author lwq
 */
public class IpUtil {

    /**
     * 太平洋ip归属地api 示例：http://whois.pconline.com.cn/ipJson.jsp?ip=xxx.xxx.xxx.xxx&json=true
     */
    private static final String TPYAPI = "http://whois.pconline.com.cn/ipJson.jsp";
    /**
     * ip-api 归属地api 示例:http://ip-api.com/json/115.191.200.34?lang=zh-CN
     */
    private static final String IPAPI = "http://ip-api.com/json/";


    public static String getIpAddress(HttpServletRequest request) {
        return StringUtils.isEmpty(request.getHeader("X-Real-IP")) ? request.getRemoteHost()
                : request.getHeader("X-Real-IP");
    }

    /**
     * 获得外网IP
     *
     * @return 外网IP
     */
    public static String getCommonalityIp() {
        try {
            return HttpUtil.get("http://bot.whatismyipaddress.com");
        } catch (Exception e) {
            return "127.0.0.1";
        }
    }

    public static JSONObject tpy(String ip) {
        try {
            StringBuffer ipUrl = new StringBuffer(TPYAPI).append("?ip=").append(ip)
                    .append("&json=true");
            return JSON.parseObject(HttpUtil.get(ipUrl.toString()));
        } catch (Exception e) {
            return null;
        }
    }

    public static JSONObject ipApi(String ip) {
        try {
            StringBuffer ipUrl = new StringBuffer(IPAPI).append("/").append(ip).append("?lang=zh-CN")
                    .append("&json=true");
            return JSON.parseObject(HttpUtil.get(ipUrl.toString()));
        } catch (Exception e) {
            return null;
        }
    }

}
