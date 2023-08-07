package com.central.backend.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CopyBettingSiteDto {
    @ApiModelProperty(value = "源商户ID")
    private Integer sourceSiteId;
    @ApiModelProperty(value = "目标商户ID")
    private Integer targetSiteId;
    @ApiModelProperty(value = "目标商户编码")
    private String targetSiteCode;

    @ApiModelProperty(value = "目标商户名称")
    private String targetSiteName;
}
