package com.common.result;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * lwq
 */
@Data
@NoArgsConstructor
public class Msg {

    /**
     * 状态
     */
    private int code = 0;
    /**
     * 结果集
     */
    private Object data;
    /**
     * 提示内容
     */
    private String msg = "请求成功";
    /**
     * 当前时间
     */
    private Long timestamp = System.currentTimeMillis();

    public Msg(Object data) {
        this.data = data;
    }
}
