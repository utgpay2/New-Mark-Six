package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_topic")
@ApiModel("站点专题")
public class KpnSiteTopic extends SuperEntity{

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

    @ApiModelProperty(value = "排序(越大越靠前)")
    private Long sort;

    @ApiModelProperty(value = "点击量")
    private Long hits;

    @ApiModelProperty(value = "状态 0待发布,1上架,2下架")
    private Integer status;

    @ApiModelProperty(value = "排版id")
    private Integer composingId;

}
