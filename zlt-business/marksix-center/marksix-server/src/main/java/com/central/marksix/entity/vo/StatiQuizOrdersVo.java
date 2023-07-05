package com.central.marksix.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 下注分类
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class StatiQuizOrdersVo {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "统计订单金额")
    private BigDecimal statiTotalPrice;
    @ApiModelProperty(value = "统计赢取金额")
    private BigDecimal statiWinMount;
    }
