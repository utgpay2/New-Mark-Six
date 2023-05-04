package com.central.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 玩法详情
 */
@Data
@ToString
@ApiModel("玩法下注详情")
public class LotteryBetPlayTypeDetailDto implements Serializable {

    @ApiModelProperty("玩法")
    private String betPlayType;

    @ApiModelProperty("1组合, 0不组合")
    private Integer combine;

    @ApiModelProperty("下注金额")
    private Long betMoney;

}
