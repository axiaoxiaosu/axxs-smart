package com.smart.api.request;

import com.common.result.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lwq
 */
@ApiModel("用户列表请求数据")
@Data
public class GetUserRequest extends PageRequest {

    @ApiModelProperty("用户姓名")
    public String username;

}
