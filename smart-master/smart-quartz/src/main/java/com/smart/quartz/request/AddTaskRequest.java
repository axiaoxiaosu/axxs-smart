package com.smart.quartz.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lwq
 */
@Data
@ApiModel("添加任务")
public class AddTaskRequest {

    /**
     * 任务名称
     */
    @ApiModelProperty("任务名称")
    private String jobName;

    /**
     * cron表达式
     */
    @ApiModelProperty(value = "cron表达式", readOnly = true)
    private String cronExpression;

    /**
     * 任务组名
     */
    @ApiModelProperty(value = "任务组名", readOnly = true)
    private String jobGroup;

    /**
     * 目标字符串
     */
    @ApiModelProperty(value = "目标字符串", readOnly = true)
    private String invokeTarget;

    /**
     * 是否允许并发  0不允许 1允许
     */
    @ApiModelProperty(value = "是否允许并发  0不允许 1允许")
    private String concurrency = "0";
}
