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
@TableName("kpn_site_platform")
@ApiModel("商户平台配置")
public class SitePlatform extends SuperEntity{

    @ApiModelProperty(value = "商户id")
    private Long siteId;

    @ApiModelProperty(value = "商户编码")
    private String siteCode;

    @ApiModelProperty(value = "商户名称")
    private String siteName;

    @ApiModelProperty(value = "兑换M币数")
    private BigDecimal exchange;

    @ApiModelProperty(value = "试看时长(秒)")
    private Integer tryTime;

    @ApiModelProperty(value = "防丢失域名")
    private String lostDomain;
}
