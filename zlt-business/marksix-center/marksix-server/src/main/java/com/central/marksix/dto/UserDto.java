package com.central.marksix.dto;

import com.central.common.dto.ProxyAdminDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto  extends ProxyAdminDto {

    @ApiModelProperty(value = "代理用户名")
    String proxyUsername;
    @ApiModelProperty(value = "是否测试账号 0:正式账号 1:测试账号")
    private Integer isTestAccount;

}
