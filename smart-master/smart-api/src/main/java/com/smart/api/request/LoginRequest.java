package com.smart.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lwq
 */
@ApiModel(value = "登陆")
@Data
public class LoginRequest {

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户名不能为空哦")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空哦")
    private String password;
}
