package com.central.backend.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class KpnSiteChannelUpdateCo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "频道状态 false下架,true上架")
    private Boolean status;

    private String updateBy;
}
