package com.central.porn.entity.vo;

import com.central.common.vo.LanguageNameMulti;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("演员信息")
public class KpnActorVo extends LanguageNameMulti implements Serializable {

    @ApiModelProperty("演员id")
    private Long id;

    @ApiModelProperty("演员名称(多语言)")
    private String name;
//
//    @ApiModelProperty("演员中文名称")
//    private String nameZh;
//
//    @ApiModelProperty("演员英文名称")
//    private String nameEn;

//    @ApiModelProperty("演员柬文名称")
//    private String nameKh;

    @ApiModelProperty(value = "性别 0女 1男")
    private Integer sex;

    @ApiModelProperty(value = "生日 年月日")
    private String birthday;

    @ApiModelProperty(value = "国籍")
    private String country;

    @ApiModelProperty(value = "身高")
    private BigDecimal height;

    @ApiModelProperty(value = "体重(KG)")
    private BigDecimal weight;

    @ApiModelProperty(value = "三围")
    private String bwh;

    @ApiModelProperty(value = "罩杯")
    private String cup;

    @ApiModelProperty(value = "头像url")
    private String avatarUrl;

    @ApiModelProperty(value = "写真照url")
    private String portraitUrl;

    @ApiModelProperty(value = "兴趣")
    private String interest;

    @ApiModelProperty(value = "简介")
    private String remark;

    @ApiModelProperty(value = "站点影片数")
    private Long movieNum;

    @ApiModelProperty(value = "站点收藏量")
    private Long favorites;

    @ApiModelProperty("演员收藏状态 false未收藏,true已收藏")
    private Boolean hasFavor ;
}
