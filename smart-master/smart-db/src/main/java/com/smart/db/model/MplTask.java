package com.smart.db.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * mpl_task
 *
 * @author
 */
@Data
@Accessors(chain = true)
public class MplTask implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private Long jobId;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 任务组名
     */
    private String jobGroup;
    /**
     * 目标字符串
     */
    private String invokeTarget;
    /**
     * 是否允许并发  0不允许 1允许
     */
    private String concurrency;
    /**
     * 状态(0正常 1停止)
     */
    private String status = "0";
    /**
     * 执行次数
     */
    private Integer count = 0;
    /**
     * 最后一次执行时间
     */
    private Date lastTime;
    /**
     * 执行策略
     */
    private String misfirePolicy = "0";
}