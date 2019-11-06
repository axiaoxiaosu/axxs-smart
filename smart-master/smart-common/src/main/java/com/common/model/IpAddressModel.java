package com.common.model;

import cn.hutool.core.date.DateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lwq
 */
@Data
@Accessors(chain = true)
public class IpAddressModel {

    /**
     * long 类型 ip
     */
    private long ip;
    /**
     * 功能
     */
    private String func;
    /**
     * 国家
     */
    private String country;
    /**
     * 城市
     */
    private String regionName;
    /**
     * 操作时间
     */
    private DateTime time;


}
