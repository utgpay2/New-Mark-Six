package com.central.common.model;

import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 开奖规则主表
 *
 * @author zlt
 * @date 2023-05-09 18:41:24
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_quiz")
public class Quiz extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "图片")
    private String picture;
    @ApiModelProperty(value = "状态 1：启用 0：禁用")
    private Integer status;
    @ApiModelProperty(value = "站点分类ID")
    private Integer siteCategoryId;
    @ApiModelProperty(value = "顺序")
    private Integer sort;
    }
