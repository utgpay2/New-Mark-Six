package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zlt
 * 用户日志实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_login_log")
@ApiModel("用户日志实体")
public class SiteLoginLog extends SuperEntity {

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

	@ApiModelProperty(value = "昵称")
	private String nickName;

	@ApiModelProperty(value = "是否vip 0/false:普通会员,1/true:vip")
	private Boolean vip;

	@ApiModelProperty(value = "登录ip")
	private String loginIp;

	@ApiModelProperty(value = "登录时间")
	private Date loginTime;

	@ApiModelProperty(value = "登录国家")
	private String loginLocation;

	@ApiModelProperty(value = "设备")
	private String loginDevice;

	@ApiModelProperty(value = "账号类型：APP：前端app用户，APP_GUEST：前端app游客用户，BACKEND：后端管理用户")
	private String type;

	@ApiModelProperty(value = "账号权限id(管理员)")
	private Long roleId;
}
