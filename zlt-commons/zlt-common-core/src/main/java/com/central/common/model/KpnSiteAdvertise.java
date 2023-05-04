package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_advertise")
@ApiModel("站点广告")
public class KpnSiteAdvertise extends SuperEntity {

    @ApiModelProperty(value = "站点id")
    private Long siteId;

    @ApiModelProperty(value = "站点编码")
    private String siteCode;

    @ApiModelProperty(value = "站点名称")
    private String siteName;

    @ApiModelProperty(value = "广告名称中文")
    private String nameZh;

    @ApiModelProperty(value = "广告名英文")
    private String nameEn;

    @ApiModelProperty(value = "广告名称柬")
    private String nameKh;

    @ApiModelProperty(value = "投放平台(H5,PC)")
    private String device;

    @ApiModelProperty(value = "投放位置 1首页轮播图,2首页平台展示,3首页专题广告,4福利,5游戏轮播图,6游戏广告")
    private Integer position;

    @ApiModelProperty(value = "排序(越大越靠前)")
    private Long sort;

    @ApiModelProperty(value = "点击量")
    private Long hits;

    @ApiModelProperty(value = "状态 0/false下架,1/true上架")
    private Boolean status;

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty(value = "广告url")
    private String url;

    @ApiModelProperty(value = "链接url")
    private String linkUrl;

    @ApiModelProperty(value = "备注")
    private String remark;
}
