package com.central.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserTansterMoneyDto {

    @ApiModelProperty(value = "会员id")
    private Long userId;

    @ApiModelProperty(value = "账变金额")
    private BigDecimal money;
}
