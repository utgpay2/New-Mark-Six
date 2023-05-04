package com.central.backend.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("管理员登录日志查询")
public class LoginLogAdminDto implements Serializable {

    private static final long serialVersionUID = 2569874565858858L;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "权限名称")
    private String roleName;

    @ApiModelProperty(value = "账号权限id(管理员)")
    private Long roleId;

    @ApiModelProperty(value = "登录时间")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String loginTime;

    @ApiModelProperty(value = "登录ip")
    private String loginIp;

    @ApiModelProperty(value = "登录国家")
    private String loginLocation;

    @ApiModelProperty(value = "设备")
    private String loginDevice;
}
