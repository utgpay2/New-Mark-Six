package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_channel")
@ApiModel("站点频道")
public class KpnSiteChannel extends SuperEntity {

    @ApiModelProperty(value = "站点id")
    private Long siteId;

    @ApiModelProperty(value = "站点编码")
    private String siteCode;

    @ApiModelProperty(value = "站点名称")
    private String siteName;

    @ApiModelProperty(value = "频道名称(中文)")
    private String nameZh;

    @ApiModelProperty(value = "频道名称(英文)")
    private String nameEn;

    @ApiModelProperty(value = "频道名称(柬文)")
    private String nameKh;

    @ApiModelProperty(value = "图标url")
    private String icon;

    @ApiModelProperty(value = "排序(越大越靠前)")
    private Long sort;

    @ApiModelProperty(value = "频道状态 0/false:下架,1/true:上架")
    private Boolean status;

    @ApiModelProperty(value = "关联标签(tag_id_1,tag_id_2,....)")
    private String tags;

    @TableField(exist = false)
    @ApiModelProperty(value = "关联标签名称")
    private String tagsName;

    @ApiModelProperty(value = "是否固定频道 0自定义,1内置固定")
    private Boolean isStable;

}
