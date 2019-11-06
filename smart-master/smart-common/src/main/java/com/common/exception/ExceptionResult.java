package com.common.exception;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lwq
 * @data 2019/8/29
 */
@Data
@Accessors(chain = true)
public class ExceptionResult {

    private long timestamp = System.currentTimeMillis();
    private int code = 100;
    private String msg = "请求超时,请稍后在试";
}
