package com.central.backend.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ApiModel("M币统计")
public class MoneyLogVO {


    @ApiModelProperty(value = "账变金额")
    private BigDecimal money;
    @ApiModelProperty(value = "笔数")
    private Integer totalNumber;

}
