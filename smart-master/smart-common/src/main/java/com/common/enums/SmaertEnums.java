package com.common.enums;

/**
 * @author lwq
 * @data 2019/7/17
 */
public enum SmaertEnums {

    TIMETYPE_1(1, "create"),
    TIMETYPE_2(2, "update");
    int code;
    String msg;

    SmaertEnums() {
    }

    SmaertEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
