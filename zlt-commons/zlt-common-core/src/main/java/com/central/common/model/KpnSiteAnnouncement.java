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
@TableName("kpn_site_announcement")
@ApiModel("公告实体")
public class KpnSiteAnnouncement extends SuperEntity {

	@ApiModelProperty(value = "站点id")
	private Long siteId;

	@ApiModelProperty(value = "站点编码")
	private String siteCode;

	@ApiModelProperty(value = "站点名称")
	private String siteName;

	@ApiModelProperty(value = "公告标题(中文)")
	private String titleZh;
	@ApiModelProperty(value = "公告内容(中文)")
	private String contentZh;

	@ApiModelProperty(value = "公告标题(英文)")
	private String titleEn;
	@ApiModelProperty(value = "公告内容(英文)")
	private String contentEn;

	@ApiModelProperty(value = "公告标题(柬文)")
	private String titleKh;
	@ApiModelProperty(value = "公告内容(柬文)")
	private String contentKh;

	@ApiModelProperty(value = "状态 0下架,1上架")
	private Integer status;

	@ApiModelProperty(value = "排序")
	private Long sort;


}
