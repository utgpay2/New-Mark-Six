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
@ApiModel("K币统计")
public class KpnMoneyLogVO {


    @ApiModelProperty(value = "账变金额")
    private BigDecimal money;
    @ApiModelProperty(value = "笔数")
    private Integer totalNumber;

}
