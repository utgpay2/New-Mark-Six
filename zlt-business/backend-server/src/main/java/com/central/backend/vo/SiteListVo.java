package com.central.backend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel("")
@Data
public class SiteListVo {

    @ApiModelProperty(value = "商户id")
    private Long siteId;

    @ApiModelProperty(value = "商户编码")
    private String siteCode;

    @ApiModelProperty(value = "商户名称")
    private String siteName;
}
