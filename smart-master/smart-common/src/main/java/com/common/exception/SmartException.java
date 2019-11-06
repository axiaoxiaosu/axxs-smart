package com.common.exception;

import com.common.enums.SmartExceptionEunms;

/**
 * 自定义异常
 */
public class SmartException extends RuntimeException {

    private Integer code;
    private String msg;

    /**
     * 自定义异常内容
     */
    public SmartException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public SmartException(SmartExceptionEunms smartExceptionEunms) {
        this.code = smartExceptionEunms.getCode();
        this.msg = smartExceptionEunms.getMsg();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
