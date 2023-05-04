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
@TableName("kpn_site_announcement_user")
@ApiModel("用户是否已读站点公告")
public class KpnSiteAnnouncementUser extends SuperEntity {

	@ApiModelProperty(value = "公告id")
	private Long annId;
	@ApiModelProperty(value = "用户id")
	private Long userId;
	@ApiModelProperty(value = "状态 0未读,1已读")
	private Integer isRead;

}
