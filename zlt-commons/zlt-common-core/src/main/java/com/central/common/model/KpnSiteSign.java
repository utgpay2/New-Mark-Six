package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/*
 * @Author: Lulu
 * @Date: 2023/2/2
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_sign")
@ApiModel("签到配置")
public class KpnSiteSign extends SuperEntity {
	@ApiModelProperty(value = "站点id")
	private Long siteId;

	@ApiModelProperty(value = "站点编码")
	private String siteCode;

	@ApiModelProperty(value = "站点名称")
	private String siteName;

	@ApiModelProperty(value = "累计天数")
	private Integer signDays;

	@ApiModelProperty(value = "奖励vip天数")
	private Integer rewardVip;

	@ApiModelProperty(value = "奖励K币数据")
	private BigDecimal rewardKb;




}
