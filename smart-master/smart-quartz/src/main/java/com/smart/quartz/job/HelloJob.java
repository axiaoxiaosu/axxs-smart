package com.smart.quartz.job;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Hello Job
 * </p>
 *
 * @package: com.xkcoding.task.quartz.job
 * @description: Hello Job
 * @author: yangkai.shen
 * @date: Created in 2018-11-26 13:22
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */

@Slf4j
public class HelloJob {

    public void job() {
        log.warn("hello,quartz");
    }
}