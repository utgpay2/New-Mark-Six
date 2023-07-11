package com.central.backend.model.dto;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 会员报表
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserReportFormsDto {

    @ExcelProperty(value = "用户名",index = 0)
    @ColumnWidth(20)
    private String username;

    @ExcelProperty(value = "直系上级",index = 1)
    @ColumnWidth(20)
    private String parentName;

    @ExcelProperty(value="会员余额",index = 2)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal mBalance;

    @ExcelProperty(value = "投注金额",index = 3)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal orderPrice;


    @ExcelProperty(value = "中奖金额",index = 4)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal winMount;


    @ExcelProperty(value = "会员盈利",index = 5)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal winLoseAmount;

    @ExcelProperty(value = "充值金额",index = 6)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal recharge;

    @ExcelProperty(value = "提现金额",index = 7)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal withdrawal;

    @ExcelProperty(value = "赢率",index = 8)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal winRate;

}
