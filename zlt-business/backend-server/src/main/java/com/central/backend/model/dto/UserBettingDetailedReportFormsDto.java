package com.central.backend.model.dto;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员投注记录报表
 *
 */

@JsonInclude(JsonInclude.Include.ALWAYS)
@Data
@EqualsAndHashCode(callSuper = false)
public class UserBettingDetailedReportFormsDto {

    @ApiModelProperty("用户名")
    @ExcelProperty(value = "用户名",index = 0)
    @ColumnWidth(20)
    private String username;

    @ApiModelProperty(value = "彩种")
    @ExcelProperty(value = "彩种",index = 1)
    @ColumnWidth(20)
    private String lotteryName;

    @ApiModelProperty(value = "玩法")
    @ExcelProperty(value = "玩法",index = 2)
    @ColumnWidth(20)
    private String siteCategoryName;

    @ApiModelProperty(value = "期号")
    @ExcelProperty(value = "期号",index = 3)
    @ColumnWidth(20)
    private String periods;

    @ApiModelProperty(value = "开奖号码")
    @ExcelProperty(value = "开奖号码",index = 4)
    @ColumnWidth(20)
    private String numbers;

    @ApiModelProperty(value = "投注类容")
    @ExcelProperty(value = "投注类容",index = 5)
    @ColumnWidth(20)
    private String quizIntroduces;

    @ApiModelProperty("投注金额")
    @ExcelProperty(value = "投注金额",index = 6)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal totalPrice;

    @ApiModelProperty("注数")
    @ExcelProperty(value = "注数",index = 7)
    @ColumnWidth(20)
    private Integer units;


    @ApiModelProperty("赢取金额")
    @ExcelProperty(value = "赢取金额",index = 8)
    @ColumnWidth(20)
    @NumberFormat("#0.00")
    private BigDecimal winMount;

    @ApiModelProperty("状态")
    @ExcelProperty(value = "状态",index = 9)
    @ColumnWidth(20)
    private String status;

    @ApiModelProperty("投注时间")
    @DateTimeFormat("yyyy-MM-dd hh:mm:ss")
    @ExcelProperty(value = "投注时间",index = 10)
    @ColumnWidth(20)
    private Date createTime;

    @ApiModelProperty("订单号")
    @ExcelProperty(value = "订单号",index = 11)
    @ColumnWidth(20)
    private String orderNo;
}
