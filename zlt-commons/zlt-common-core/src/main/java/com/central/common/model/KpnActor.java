package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 演员列表
 *
 * @author yixiu
 * @date 2023-02-03 16:31:09
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_actor")
public class KpnActor extends SuperEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "中文名")
    private String nameZh;
    @ApiModelProperty(value = "英文名")
    private String nameEn;
    @ApiModelProperty(value = "柬文名")
    private String nameKh;
    @ApiModelProperty(value = "性别 0女 1男")
    private Integer sex;
    @ApiModelProperty(value = "生日 年月日")
    private String birthday;
    @ApiModelProperty(value = "国籍(中文)")
    private String countryZh;
    @ApiModelProperty(value = "国籍(英文)")
    private String countryEn;
    @ApiModelProperty(value = "国籍(柬文)")
    private String countryKh;
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
    @ApiModelProperty(value = "兴趣(中文)")
    private String interestZh;
    @ApiModelProperty(value = "兴趣(英文)")
    private String interestEn;
    @ApiModelProperty(value = "兴趣(柬文)")
    private String interestKh;
    @ApiModelProperty(value = "简介")
    private String remark;

    @ApiModelProperty(value = "总收藏量(所有站点只和)")
    private Long favorites;
}
