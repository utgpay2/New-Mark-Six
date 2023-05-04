package com.central.backend.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class KpnSiteUpdateCo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "状态 0/false:下架,1/true:上架")
    private Boolean status;

    @ApiModelProperty(value = "站点维护状态 0/false:未维护,1/true:维护中")
    private Boolean repairStatus;

    private String updateBy;
}
