package com.common.enums;

/**
 * @author lwq
 * @data 2019/7/17
 */
public enum UserEnum {

    STATU_0(0, "有效"),
    STATU_1(1, "失效"),
    ;
    public int code;
    public String msg;

    UserEnum() {
    }

    UserEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
