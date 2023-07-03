package com.central.marksix.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 
 *
 * @author zlt
 * @date 2023-06-15 20:50:53
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class QuizSubordersDto {
    @ApiModelProperty(value = "下注内容")
    private String bettingContent;
    @ApiModelProperty(value = "订单金额")
    private BigDecimal totalPrice;
    @ApiModelProperty(value = "注数")
    private Integer units;
    @ApiModelProperty(value = "赔率")
    private Integer odds;
    }
