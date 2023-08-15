package com.proxy.center.model.dto;

import com.central.common.dto.ProxyAdminDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto  extends ProxyAdminDto {

    @ApiModelProperty(value = "代理用户名")
    String proxyUsername;

}
