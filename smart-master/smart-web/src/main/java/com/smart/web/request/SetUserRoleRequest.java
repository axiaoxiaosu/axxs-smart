package com.smart.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lwq
 * @data 2019/7/17
 */
@ApiModel("关联用户和角色")
@Data
public class SetUserRoleRequest {

    @ApiModelProperty(value = "用户id", readOnly = true)
    private String userid;
    @ApiModelProperty(value = "角色id", readOnly = true)
    private String roleid;

}
