package com.central.marksix.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 号码属性表
 *
 * @author zlt
 * @date 2023-05-08 15:05:53
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ZodiacBumpLotteryBetDto {
    @ApiModelProperty(value = "开奖分类二类名称")
    private String quizTitle;
    @ApiModelProperty(value = "生肖一")
    private String zodiacOne;
    @ApiModelProperty(value = "生肖二")
    private String zodiacTwo;
    @ApiModelProperty(value = "赔率一")
    private Double oddsOne;
    @ApiModelProperty(value = "赔率二")
    private Double oddsTwo;
}
