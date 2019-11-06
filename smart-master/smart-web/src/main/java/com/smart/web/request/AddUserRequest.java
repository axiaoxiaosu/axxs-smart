package com.smart.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lwq
 */

@Data
@ApiModel("添加用户")
@Accessors(chain = true)
public class AddUserRequest {

    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("用户头像")
    private String portrait;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("备注")
    private String remark;
}
