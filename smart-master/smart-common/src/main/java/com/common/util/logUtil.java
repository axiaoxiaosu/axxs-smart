package com.common.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lwq
 */
@Slf4j
public class logUtil {

    private String msg;

    public static void logError(Exception e, String errorMsg) {
        log.error("时间:[{}],\n异常信息:[{}],\n自定义异常输出：", DateTime.now(), ExceptionUtil.getMessage(e), errorMsg);
    }

}
