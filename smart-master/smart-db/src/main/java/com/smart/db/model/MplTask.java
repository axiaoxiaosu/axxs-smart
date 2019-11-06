package com.smart.db.model;

import com.common.constants.QuartzConstant;
import java.io.Serializable;

/**
 * mpl_task
 *
 * @author
 */
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
    private String status;
    /**
     * 执行次数
     */
    private Integer count;
    /**
     * 执行策略
     */
    private String misfirePolicy = QuartzConstant.MISFIRE_DEFAULT;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getInvokeTarget() {
        return invokeTarget;
    }

    public void setInvokeTarget(String invokeTarget) {
        this.invokeTarget = invokeTarget;
    }

    public String getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(String concurrency) {
        this.concurrency = concurrency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMisfirePolicy() {
        return misfirePolicy;
    }

    public void setMisfirePolicy(String misfirePolicy) {
        this.misfirePolicy = misfirePolicy;
    }
}