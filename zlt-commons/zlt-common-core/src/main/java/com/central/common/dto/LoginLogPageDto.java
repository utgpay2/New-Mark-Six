package com.central.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("用户日志查询")
public class LoginLogPageDto implements Serializable {

    private static final long serialVersionUID = 447547858475858858L;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "登录时间")
    private String loginTime;
    @ApiModelProperty(value = "登录ip")
    private String loginIp;

    @ApiModelProperty(value = "登录国家")
    private String loginLocation;

    @ApiModelProperty(value = "设备")
    private String loginDevice;

    @ApiModelProperty(value = "厂商Code")
    private String merchatCode;
}
