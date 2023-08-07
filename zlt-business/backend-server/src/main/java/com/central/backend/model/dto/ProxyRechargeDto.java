package com.central.backend.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel
public class ProxyRechargeDto {

    @ApiModelProperty(value = "代理id" , required = true)
    @NotNull(message = "代理id不能为空")
    private Integer userId;

    @ApiModelProperty(value = "正式授信金额" , required = true)
    @NotNull(message = "正式授信金额不能为空")
    private BigDecimal amount;

    @ApiModelProperty(value = "测试授信金额" , required = true)
    @NotNull(message = "测试授信金额不能为空")
    private BigDecimal testAmount;

    @ApiModelProperty(value = "转账类型 0收回代理授信额度  1给代理授信额度")
    private Integer type;
}
