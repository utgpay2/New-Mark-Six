package com.central.backend.model.dto;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员资金明细报表
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserMoneyDetailedReportFormsDto {

    @ExcelProperty(value = "用户名",index = 0)
    @ColumnWidth(20)
    private String username;

    @ExcelProperty(value = "流水号",index = 1)
    @ColumnWidth(20)
    private String orderNo;

    @ExcelProperty(value="摘要",index = 2)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private String orderType;

    @ExcelProperty(value = "收入金额",index = 3)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal income;


    @ExcelProperty(value = "支出金额",index = 4)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal expenditure;


    @ExcelProperty(value = "用户余额",index = 5)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal balance;


    @DateTimeFormat("yyyy-MM-dd hh:mm:ss")
    @ExcelProperty(value = "时间",index = 6)
    @ColumnWidth(20)
    private Date createTime;


}
