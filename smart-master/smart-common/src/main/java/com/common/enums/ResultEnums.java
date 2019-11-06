package com.common.enums;

/**
 * @author lwq
 */
public enum ResultEnums {
    REQUEST_SUCCESS(0, "请求成功");

    private int code;
    private String msg;

    ResultEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
