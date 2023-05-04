package com.central.porn.entity.vo;

import com.central.common.vo.LanguageNameMulti;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("会员频道")
public class KpnMemberChannelVo extends LanguageNameMulti implements Serializable {
    @ApiModelProperty(value = "频道id")
    private Long id;

    @ApiModelProperty(value = "频道名称(多语言)")
    private String name;

//    @ApiModelProperty("频道中文名称")
//    private String nameZh;
//
//    @ApiModelProperty("频道英文名称")
//    private String nameEn;
//
//    @ApiModelProperty("频道柬文名称")
//    private String nameKh;

    @ApiModelProperty(value = "频道图标")
    private String icon;

    @ApiModelProperty(value = "排序(越大越靠前)")
    private Long sort;
    @ApiModelProperty(value = "是否固定频道 0/false自定义,1/true内置固定")
    private Boolean isStable;
}
