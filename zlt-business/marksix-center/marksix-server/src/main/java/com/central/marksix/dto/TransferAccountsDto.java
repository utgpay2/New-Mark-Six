package com.central.marksix.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class TransferAccountsDto {

    @ApiModelProperty(value = "代理用户名")
    private String username;

    @ApiModelProperty(value = "随机字符串")
    private String random;
    @ApiModelProperty(value = "站点编码")
    private String siteCode;
    @ApiModelProperty(value = "签名摘要")
    private String sign;

    @ApiModelProperty(value = "会员id")
    private long userId;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "转账类型") //0给会员下分  1给会员上分
    private Integer type;

}
