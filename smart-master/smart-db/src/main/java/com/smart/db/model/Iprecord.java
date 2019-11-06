package com.smart.db.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * iprecord
 * @author 
 */
@Data
@Accessors(chain = true)
public class Iprecord implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * ip(long类型)
     */
    private Long ip;

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
    private String city;

    /**
     * 地区
     */
    private String regionName;

    /**
     * 记录时间
     */
    private Date time;

    /**
     * 人数统计
     */
    private int count;

    /**
     * 地址
     */
    private String Address;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIp() {
        return ip;
    }

    public void setIp(Long ip) {
        this.ip = ip;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}