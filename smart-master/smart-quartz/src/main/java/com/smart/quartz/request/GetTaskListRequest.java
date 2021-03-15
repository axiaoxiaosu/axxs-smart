package com.smart.quartz.request;

import com.common.result.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lwq
 */
@Data
@ApiModel("获取任务列表请求条件")
public class GetTaskListRequest extends PageRequest {


    @ApiModelProperty("任务名称")
    private String jobName;

}
