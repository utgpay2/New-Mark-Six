package com.proxy.center.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel
public class SiteRechargeDto {

    @ApiModelProperty(value = "商户id" , required = true)
    @NotNull(message = "商户id不能为空")
    private Integer siteId;

    @ApiModelProperty(value = "正式授信金额" , required = true)
    @NotNull(message = "正式授信金额不能为空")
    private BigDecimal amount;

    @ApiModelProperty(value = "测试授信金额" , required = true)
    @NotNull(message = "测试授信金额不能为空")
    private BigDecimal testAmount;
}
