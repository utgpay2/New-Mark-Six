package com.central.backend.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class KpnSiteAdvertiseUpdateCo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "状态 0/false下架,1/true上架")
    private Boolean status;

    private String updateBy;
}
