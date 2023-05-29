package com.central.marksix.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.SuperEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 
 *
 * @author zlt
 * @date 2023-05-09 18:43:48
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class QuizOrdersDto{
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "期数")
    private String periods;
    @ApiModelProperty(value = "年份")
    private Integer year;
    @ApiModelProperty(value = "站点彩种ID")
    private Long siteLotteryId;
    @ApiModelProperty(value = "站点彩种名称")
    private String lotteryName;
    @ApiModelProperty(value = "站点下注分类ID")
    private Long siteCategoryId;
    @ApiModelProperty(value = "站点下注分类名称")
    private Long siteCategoryName;
    @ApiModelProperty(value = "投注内容")
    private String bettingContent;

    @ApiModelProperty(value = "开奖规则主表ID")
    private Long quizId;
    @ApiModelProperty(value = "开奖规则主表标题")
    private String quizTitle;
    @ApiModelProperty(value = "开奖规则明细ID")
    private Long quizChooseId;
    @ApiModelProperty(value = "开奖规则明细名称")
    private String quizIntroduce;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal totalPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public BigDecimal getTotalPrice() {
        return totalPrice == null ? BigDecimal.ZERO.setScale(2) : totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    @ApiModelProperty(value = "注数")
    private Integer units;
    @ApiModelProperty(value = "赔率")
    private Double odds;
    }
