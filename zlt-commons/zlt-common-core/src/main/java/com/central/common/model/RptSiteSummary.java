package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("rpt_site_summary")
@ApiModel("商户运营报表")
public class RptSiteSummary extends SuperEntity{

    @ApiModelProperty(value = "商户id")
    private Long siteId;

    @ApiModelProperty(value = "商户编码")
    private String siteCode;

    @ApiModelProperty(value = "商户名称")
    private String siteName;

    @ApiModelProperty(value = "统计日期(年月日)")
    private String date;

    @ApiModelProperty(value = "pv")
    private Long pv;

    @ApiModelProperty(value = "uv")
    private Long uv;


    @ApiModelProperty(value = "新增会员数")
    private int newUserNum;


    @ApiModelProperty(value = "充值人数")
    private int rechargeNumber;
    @ApiModelProperty(value = "充值金额")
    private BigDecimal recharge;



}
