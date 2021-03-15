package com.common.result;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lwq 默认分页请求参数
 * @data 2019/8/28
 */
@Data
@Accessors(chain = true)
public class PageRequest {

    /**
     * 页码 缺省1
     */
    private int pageNum = 1;
    /**
     * 每页显示条数 缺省10
     */
    private int pageSize = 10;

}

