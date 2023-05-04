package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 * @Author: Lulu
 * @Date: 2023/2/1
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_suggestion")
@ApiModel("意见反馈")
public class KpnSiteSuggestion extends SuperEntity {
	@ApiModelProperty(value = "站点id")
	private Long siteId;

	@ApiModelProperty(value = "站点编码")
	private String siteCode;

	@ApiModelProperty(value = "站点名称")
	private String siteName;

	@ApiModelProperty(value = "会员id")
	private Long userId;


	@ApiModelProperty(value = "会员账号")
	private String userName;

	@ApiModelProperty(value = "意见反馈")
	private String content;
	@ApiModelProperty(value = "手机号")
	private String mobile;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "状态 0待处理,1已处理")
	private Integer status;



}
