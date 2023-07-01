package com.central.marksix.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.SuperEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

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
    @ApiModelProperty(value = "期数" ,required = true)
    private String periods;
    @ApiModelProperty(value = "年份",required = true)
    private Integer year;
    @ApiModelProperty(value = "彩种ID",required = true)
    private Long lotteryId;
    @ApiModelProperty(value = "站点彩种ID",required = true)
    private Long siteLotteryId;
    @ApiModelProperty(value = "站点彩种名称",required = true)
    private String lotteryName;
    @ApiModelProperty(value = "站点下注分类一类ID",required = true)
    private Long siteCategoryId;
    @ApiModelProperty(value = "站点下注分类一类名称",required = true)
    private String siteCategoryName;
    @ApiModelProperty(value = "投注内容",required = true)
    private String bettingContent;

    @ApiModelProperty(value = "开奖分类二类ID",required = true)
    private Long quizId;
    @ApiModelProperty(value = "开奖分类二类名称",required = true)
    private String quizTitle;
    @ApiModelProperty(value = "开奖分类三类ID",required = true)
    private Long quizDetailsId;
    @ApiModelProperty(value = "开奖分类三类名称",required = true)
    private String quizDetailsName;
    @ApiModelProperty(value = "开奖规则明细ID",required = true)
    private Long quizChooseId;
    @ApiModelProperty(value = "开奖规则明细名称",required = true)
    private String quizIntroduce;

    @ApiModelProperty(value = "订单金额",required = true)
    private BigDecimal totalPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public BigDecimal getTotalPrice() {
        return totalPrice == null ? BigDecimal.ZERO.setScale(2) : totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    @ApiModelProperty(value = "注数",required = true)
    private Integer units;
    @ApiModelProperty(value = "赔率",required = true)
    private Double odds;
    @ApiModelProperty(value = "子订单：复式，胆拖")
    private List<QuizSubordersDto> subordersList;
    }
