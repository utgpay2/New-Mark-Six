package com.central.porn.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("支付银行卡信息")
public class KpnSiteBankCardPayVo implements Serializable {

    @ApiModelProperty("收款卡id")
    private Long id;

    @ApiModelProperty("银行名称")
    private String bankName;

    @ApiModelProperty("银行卡号")
    private String card;

    @ApiModelProperty("持卡姓名")
    private String account;

    @ApiModelProperty("银行卡排序(越大越靠前)")
    private Integer sort;

    @ApiModelProperty("支付金额(现金)")
    private BigDecimal amount;

    @ApiModelProperty("币种")
    private String currency;
}
