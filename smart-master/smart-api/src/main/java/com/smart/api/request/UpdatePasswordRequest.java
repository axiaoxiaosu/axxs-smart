package com.smart.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author lwq
 * @data 2019/7/16
 */
@ApiModel(value = "修改密码")
@Data
public class UpdatePasswordRequest {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", readOnly = true)
    @NotBlank(message = "用户名不能为空")
    @Length(min = 1, max = 30, message = "用户名长度不对")
    private String username;

    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码", readOnly = true)
    @NotBlank(message = "新密码不能为空")
    @Length(min = 1, max = 30, message = "新密码长度不对")
    private String newpassword;

    /**
     * 原来的密码
     */
    @ApiModelProperty(value = "原密码", readOnly = true)
    @NotBlank(message = "原密码不能为空")
    @Length(min = 1, max = 30, message = "原密码长度不对")
    private String password;
}
