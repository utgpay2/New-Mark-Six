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
@ApiModel("签到结果")
public class KpnSiteUserSignResultVo implements Serializable {

    @ApiModelProperty("累计签到天数")
    private Integer days;

    @ApiModelProperty("是否获得奖励")
    private Boolean isReward = Boolean.FALSE;

    @ApiModelProperty("奖励天数")
    private Integer rewardVip;

    @ApiModelProperty("奖励k币数")
    private BigDecimal rewardKb;
}
