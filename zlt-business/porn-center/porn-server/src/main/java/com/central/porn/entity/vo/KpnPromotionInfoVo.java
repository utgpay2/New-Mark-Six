package com.central.porn.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("我的累计推广数据")
public class KpnPromotionInfoVo implements Serializable {
    @ApiModelProperty("推广码")
    private String promotionCode;

    @ApiModelProperty("人数")
    private Integer members;

    @ApiModelProperty("累计奖励vip天数")
    private Integer vipDays;

    @ApiModelProperty("累计奖励K币数")
    private BigDecimal kbs;

}
