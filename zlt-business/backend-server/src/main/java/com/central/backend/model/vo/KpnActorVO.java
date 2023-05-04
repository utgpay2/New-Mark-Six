package com.central.backend.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.KpnActor;
import com.central.common.model.SuperEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
public class KpnActorVO{
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "演员ID")
    private Long id;
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
    @ApiModelProperty(value = "影片数量")
    private Integer total;
    @ApiModelProperty(value = "影片收藏量")
    private Integer favorites;
    @ApiModelProperty(value = "播放量")
    private Integer vv;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "更新人")
    private String updateBy;

    }
