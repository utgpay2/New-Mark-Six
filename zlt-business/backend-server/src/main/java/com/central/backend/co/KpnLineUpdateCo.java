package com.central.backend.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class KpnLineUpdateCo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "状态 0:禁用,1:启用")
    private Integer status;

    private String updateBy;
}
