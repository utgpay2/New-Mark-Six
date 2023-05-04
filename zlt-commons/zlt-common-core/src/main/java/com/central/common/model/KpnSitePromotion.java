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
@TableName("kpn_site_promotion")
@ApiModel("推广好友配置")
public class KpnSitePromotion extends SuperEntity {
	@ApiModelProperty(value = "站点id")
	private int siteId;

	@ApiModelProperty(value = "站点编码")
	private String siteCode;

	@ApiModelProperty(value = "站点名称")
	private String siteName;

	@ApiModelProperty(value = "邀请人获取vip天数")
	private Integer inviteVip;

	@ApiModelProperty(value = "邀请人获取k币数")
	private BigDecimal inviteKb;


	@ApiModelProperty(value = "邀请人单日k币上限")
	private BigDecimal kbDayLimit;

	@ApiModelProperty(value = "被邀请人获取vip天数")
	private Integer beInvitedVip;

	@ApiModelProperty(value = "被邀请人获取K币数")
	private BigDecimal beInvitedKb;



}
