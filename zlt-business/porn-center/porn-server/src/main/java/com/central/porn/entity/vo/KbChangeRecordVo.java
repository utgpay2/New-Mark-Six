package com.central.porn.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("账变信息")
public class KbChangeRecordVo {

    @ApiModelProperty("账变日期")
    private Date dateTime;

    @ApiModelProperty("账变类型 支出/收入(多语言)")
    private String addOrSubType;

    @ApiModelProperty("账变项目(多语言)")
    private String itemName;

    @ApiModelProperty("账变类型 -1支出/1收入")
    private Integer addOrSubTypeInt;

    @ApiModelProperty("账变K币数")
    private BigDecimal kbs;
}
