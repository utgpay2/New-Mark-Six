package com.central.porn.entity.vo;

import com.central.common.vo.LanguageNameMulti;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("站点频道")
public class KpnSiteChannelVo  extends LanguageNameMulti implements Serializable {
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "频道名称(多语言)")
    private String name;

//    @ApiModelProperty(value = "频道名称(中文)")
//    private String nameZh;
//
//    @ApiModelProperty(value = "频道名称(英文)")
//    private String nameEn;
//
//    @ApiModelProperty(value = "频道名称(柬文)")
//    private String nameKh;

    @ApiModelProperty(value = "排序(越大越靠前)")
    private Long sort;

    @ApiModelProperty(value = "是否固定频道 0自定义,1内置固定")
    private Boolean isStable;

    @ApiModelProperty(value = "是否推荐页")
    private Boolean isRecommend;

    @ApiModelProperty(value = "是否找片页")
    private Boolean isSearch;

    @ApiModelProperty(value = "最新")
    private Boolean isNewest;

    @ApiModelProperty(value = "最热")
    private Boolean isHottest;

    @ApiModelProperty(value = "图标url")
    private String icon;
}
