package com.central.backend.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("角色查询")
@Data
public class SysRoleDto implements Serializable {

    private static final long serialVersionUID = 52548995414548L;

    @ApiModelProperty(value = "账号权限id")
    private Long id;

    @ApiModelProperty(value = "权限名称")
    private String name;
}
