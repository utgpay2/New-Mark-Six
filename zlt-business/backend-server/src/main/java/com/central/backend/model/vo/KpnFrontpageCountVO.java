package com.central.backend.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.utils.Decimal2Serializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 首页访问量统计
 *
 * @author yixiu
 * @date 2023-02-09 19:41:45
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class KpnFrontpageCountVO {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "访问量")
    private Long pvCount;
    @ApiModelProperty(value = "独立访客数")
    private Long uvCount;
    @ApiModelProperty(value = "在线人数")
    private Long onlineUsers;
    @ApiModelProperty(value = "充值单数")
    private Long rechargeNumber;
    @ApiModelProperty(value = "充值金额")
    @JsonSerialize(using = Decimal2Serializer.class, nullsUsing = Decimal2Serializer.class)
    private BigDecimal rechargeAmount;
    @ApiModelProperty(value = "每日新增会员人数")
    private Long addUsers;
    }
