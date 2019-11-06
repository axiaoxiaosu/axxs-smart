package com.smart.db.model;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * mpl_task_log
 *
 * @author
 */
@Accessors(chain = true)
@Data
public class MplTaskLog implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * job_id
     */
    private String jobId;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * corn表达式
     */
    private String cronExpression;
    /**
     * 耗时
     */
    private Long timeConsuming;
    /**
     * 任务组名
     */
    private String jobGroup;
    /**
     * 执行目标
     */
    private String invokeTarget;
    /**
     * 提示
     */
    private String msg;
    /**
     * 异常
     */
    private String error;

}