package com.common.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author lwq
 * @data 2019/9/2
 */
@Data
@ToString
public class HttpClientResult {

    private static final long serialVersionUID = 2168152194164783950L;

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public HttpClientResult() {
    }

    public HttpClientResult(int code) {
        this.code = code;
    }

    public HttpClientResult(String content) {
        this.content = content;
    }

    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }

}
