package com.proxy.center.model.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 会员报表
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserReportFormsVo {

    @ApiModelProperty("用户名")
    @ExcelProperty(value = "用户名",index = 0)
    @ColumnWidth(20)
    private String username;

    @ApiModelProperty("直系上级")
    @ExcelProperty(value = "直系上级",index = 1)
    @ColumnWidth(20)
    private String parentName;

    @ApiModelProperty("会员余额")
    @ExcelProperty(value="会员余额",index = 2)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal mBalance;

    @ApiModelProperty("投注金额")
    @ExcelProperty(value = "投注金额",index = 3)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal orderPrice;


    @ApiModelProperty("中奖金额")
    @ExcelProperty(value = "中奖金额",index = 4)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal winMount;


    @ApiModelProperty("会员盈利")
    @ExcelProperty(value = "会员盈利",index = 5)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal winLoseAmount;

    @ApiModelProperty("充值金额")
    @ExcelProperty(value = "充值金额",index = 6)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal recharge;

    @ApiModelProperty("提现金额")
    @ExcelProperty(value = "提现金额",index = 7)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal withdrawal;

    @ApiModelProperty("赢率")
    @ExcelProperty(value = "赢率",index = 8)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal winRate;

}
