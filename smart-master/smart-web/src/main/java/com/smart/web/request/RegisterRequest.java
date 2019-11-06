package com.smart.web.request;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author lwq
 * @data 2019/7/16
 */
@ApiModel(value = "注册")
@Data
public class RegisterRequest {

    @NotBlank(message = "用户名不能为空")
    @Length(max = 30, message = "用户名长度不能大于30")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Length(max = 30, message = "密码长度不能大于30")
    private String password;
}
