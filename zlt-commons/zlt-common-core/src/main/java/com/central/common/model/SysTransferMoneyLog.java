package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_transfer_money_log")
@ApiModel("账变记录")
public class SysTransferMoneyLog extends SuperEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "第三方交易编号")
    private String traceId;

    @ApiModelProperty(value = "注单编号")
    private String betId;

    @ApiModelProperty(value = "账变金额")
    private BigDecimal money;

    @ApiModelProperty(value = "账变前金额")
    private BigDecimal beforeMoney;

    @ApiModelProperty(value = "账变后金额")
    private BigDecimal afterMoney;

    @ApiModelProperty(value = "币种")
    private String currency;

    @ApiModelProperty(value = "1:成功，2：失败")
    private Integer transferStatus;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "订单类型，3.派彩，4.下注，5.手动入款，6.手动出款，7.领取洗码，8.商户API加点,9.商户API扣点")
    private Integer orderType;

    @ApiModelProperty(value = "父级")
    private String parent;
}
