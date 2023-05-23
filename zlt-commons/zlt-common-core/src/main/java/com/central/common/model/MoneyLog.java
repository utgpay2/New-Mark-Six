package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_money_log")
@ApiModel("M币账变记录")
public class MoneyLog extends SuperEntity {

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "内部订单号")
    private String orderNo;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "账变金额")
    private BigDecimal money;
    @ApiModelProperty(value = "账变前金额")
    private BigDecimal beforeMoney;
    @ApiModelProperty(value = "账变后金额")
    private BigDecimal afterMoney;
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "账变类型")
    private Integer orderType;

    @ApiModelProperty(value = "账变类型名称")
    @TableField(exist = false)
    private String orderTypeName;

    @ApiModelProperty(value = "0失败,1成功")
    private Integer transferStatus;

}
