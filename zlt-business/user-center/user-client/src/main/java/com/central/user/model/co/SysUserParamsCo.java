package com.central.user.model.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class SysUserParamsCo {

    @ApiModelProperty(value = "管理员账号" , required = true)
    @NotNull(message = "管理员账号不能为空")
    private String username;

    @ApiModelProperty(value = "管理员密码" , required = true)
    @NotNull(message = "管理员密码不能为空")
    private String password;
}
