package com.smart.web.request;

import com.common.result.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lwq
 * @data 2019/9/6
 */
@Data
@ApiModel("查询角色信息")
public class GetRolesRequest extends PageRequest {

    @ApiModelProperty("角色名称")
    private String roleName;
}
