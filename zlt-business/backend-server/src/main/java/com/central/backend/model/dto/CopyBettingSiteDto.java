package com.central.backend.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CopyBettingSiteDto {
    @ApiModelProperty(value = "源站点ID")
    private Integer sourceSiteId;
    @ApiModelProperty(value = "目标站点ID")
    private Integer targetSiteId;
    @ApiModelProperty(value = "目标站点编码")
    private String targetSiteCode;

    @ApiModelProperty(value = "目标站点名称")
    private String targetSiteName;
}
