package com.common.enums;

/**
 * @author lwq
 */
public enum QuartzEnums {

    QUARTZ_STATUS_0(0, "正常"),
    QUARTZ_STATUS_1(1, "停止"),
    ;
    private Integer code;
    private String context;

    QuartzEnums(Integer code, String context) {
        this.code = code;
        this.context = context;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }}
