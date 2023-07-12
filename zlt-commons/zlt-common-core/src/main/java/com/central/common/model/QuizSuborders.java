package com.central.common.model;

import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * @author zlt
 * @date 2023-06-15 20:50:53
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_quiz_suborders")
public class QuizSuborders extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "父订单号")
    private String orderNo;
    @ApiModelProperty(value = "子订单号")
    private String suborderNo;
    @ApiModelProperty(value = "下注内容")
    private String bettingContent;
    @ApiModelProperty(value = "订单金额")
    private BigDecimal totalPrice;
    @ApiModelProperty(value = "赢取金额")
    private BigDecimal winMount;
    @ApiModelProperty(value = "输赢金额")
    private BigDecimal winLoseAmount;
    @ApiModelProperty(value = "注数")
    private Integer units;
    @ApiModelProperty(value = "1 待开奖,2 已取消,3 中奖,4 未中奖")
    private Integer status;
    @ApiModelProperty(value = "赔率")
    private BigDecimal odds;
    @ApiModelProperty(value = "赔率2(三中二之中三，中特之中二）")
    private Double odds2;
    }
