package com.central.backend.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class KpnSiteAnnouncementUpdateCo  {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "状态 0下架,1上架")
    private Integer status;

    private String updateBy;
}
