package com.central.porn.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("站点信息")
public class KpnSiteVo implements Serializable {

    @ApiModelProperty("站点id")
    private Long sid;

    @ApiModelProperty("站点logo")
    private String logoUrl;

    @ApiModelProperty("币种")
    private String currencyCode;
}
