package com.central.backend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel("")
@Data
public class KpnSiteVo {

    @ApiModelProperty(value = "人数")
    private Integer siteTotal;

    @ApiModelProperty(value = "会员总人数")
    private Integer memberNumTotal;

}
