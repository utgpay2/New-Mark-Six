package com.central.backend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;


@ApiModel("")
@Data
public class RptSiteSummaryVo {

    @ApiModelProperty(value = "pv")
    private Integer pvTotal;

    @ApiModelProperty(value = "uv")
    private Integer uvTotal;

    @ApiModelProperty(value = "新增会员数")
    private Integer newUserNumTotal;

    @ApiModelProperty(value = "新增vip数")
    private Integer newVipNumTotal;

    @ApiModelProperty(value = "充值金额")
    private BigDecimal rechargeTotal;




}
