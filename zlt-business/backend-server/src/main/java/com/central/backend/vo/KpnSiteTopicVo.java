package com.central.backend.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class KpnSiteTopicVo {

    @ApiModelProperty(value = "id")
    private Long id;

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

    @ApiModelProperty(value = "状态 0待发布,1上架,2下架")
    private Integer status;

    @ApiModelProperty(value = "关联影片数")
    private Integer  videosNumber;
    @ApiModelProperty(value = "点击量")
    private Integer hits;


    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "最后更新人")
    private String updateBy;

}
