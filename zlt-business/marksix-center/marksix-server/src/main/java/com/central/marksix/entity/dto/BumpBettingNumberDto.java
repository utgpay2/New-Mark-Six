package com.central.marksix.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 *
 * @author zlt
 * @date 2023-05-09 18:43:48
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BumpBettingNumberDto {
    @ApiModelProperty(value = "投注号码")
    private String bettingNumber;
    @ApiModelProperty(value = "波色")
    private String color;
    @ApiModelProperty(value = "赔率")
    private Double odds;
    }
