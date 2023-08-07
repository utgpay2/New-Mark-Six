package com.central.marksix.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("商户信息")
public class SiteVo implements Serializable {

    @ApiModelProperty("商户id")
    private Long sid;

    @ApiModelProperty("商户logo")
    private String logoUrl;

    @ApiModelProperty("币种")
    private String currencyCode;
}
