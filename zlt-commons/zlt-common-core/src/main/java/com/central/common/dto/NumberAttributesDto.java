package com.central.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 号码属性表
 *
 * @author zlt
 * @date 2023-05-08 15:05:53
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class NumberAttributesDto {
    @ApiModelProperty(value = "年份")
    private Integer year;
    @ApiModelProperty(value = "开奖号码")
    private String number;
    @ApiModelProperty(value = "生肖")
    private String zodiac;
    @ApiModelProperty(value = "家   野")
    private String poultryandbeast;
    @ApiModelProperty(value = "波色")
    private String color;
    @ApiModelProperty(value = "尾数")
    private String tail;
    @ApiModelProperty(value = "五行")
    private String fiveElements;
    @ApiModelProperty(value = "尾数大小")
    private String size;
    @ApiModelProperty(value = "数字大小")
    private String numSize;
    }
