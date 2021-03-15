package com.smart.db.beanenum;

/**
 * @author lwq
 */
public enum MplUser {

    STATUS_1(1, "有效"),
    STATUS_2(2, "冻结"),
    STATUS_3(3, "无效");
    private String msg;
    private int code;

    MplUser(int code, String msg) {
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
