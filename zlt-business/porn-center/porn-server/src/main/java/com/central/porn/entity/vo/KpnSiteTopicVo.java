package com.central.porn.entity.vo;

import com.central.common.vo.LanguageNameMulti;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("站点专题")
public class KpnSiteTopicVo extends LanguageNameMulti implements Serializable {
    @ApiModelProperty(value = "专题id")
    private Long id;

    @ApiModelProperty(value = "专题名称(多语言)")
    private String name;

//    @ApiModelProperty(value = "专题名称(中文)")
//    private String nameZh;
//
//    @ApiModelProperty(value = "专题名称(英文)")
//    private String nameEn;
//
//    @ApiModelProperty(value = "专题名称(柬文)")
//    private String nameKh;

    @ApiModelProperty(value = "排序(越大越靠前)")
    private Long sort;

    @ApiModelProperty(value = "排版id")
    private Long composingId;

    @ApiModelProperty(value = "影片信息")
    private List<KpnSiteMovieBaseVo> movieBaseVos;
}
