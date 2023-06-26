package com.central.marksix.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("账变信息详情")
public class MbChangeRecordDetailsVo extends MbChangeRecordVo {


    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("用户id")
    private String userName;
    @ApiModelProperty(value = "账变前金额")
    private BigDecimal beforeMoney;
    @ApiModelProperty(value = "账变后金额")
    private BigDecimal afterMoney;
    @ApiModelProperty(value = "账变名称")
    private String name;
}
