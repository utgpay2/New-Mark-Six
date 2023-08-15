package com.proxy.center.model.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModelProperty;
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
public class UserMoneyDetailedReportFormsVo {

    @ApiModelProperty("用户名")
    @ExcelProperty(value = "用户名",index = 0)
    @ColumnWidth(20)
    private String username;

    @ApiModelProperty("流水号")
    @ExcelProperty(value = "流水号",index = 1)
    @ColumnWidth(20)
    private String orderNo;

    @ApiModelProperty("摘要")
    @ExcelProperty(value="摘要",index = 2)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private String orderType;

    @ApiModelProperty("收入金额")
    @ExcelProperty(value = "收入金额",index = 3)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal income;

    @ApiModelProperty("支出金额")
    @ExcelProperty(value = "支出金额",index = 4)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal expenditure;

    @ApiModelProperty("用户余额")
    @ExcelProperty(value = "用户余额",index = 5)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal balance;

    @ApiModelProperty("时间")
    @DateTimeFormat("yyyy-MM-dd hh:mm:ss")
    @ExcelProperty(value = "时间",index = 6)
    @ColumnWidth(20)
    private Date createTime;


}
