package com.central.user.model.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel
public class SysUserMoneyCo {

    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "余额")
    private BigDecimal money;

    @ApiModelProperty(value = "未完成打码量")
    private BigDecimal unfinishedCode;

    @ApiModelProperty(value = "洗码额")
    private BigDecimal washCode;

}
