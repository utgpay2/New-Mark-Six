package com.central.marksix.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 
 *
 * @author zlt
 * @date 2023-05-09 18:43:48
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BettingNumberGroupVo {
    @ApiModelProperty(value = "赔率")
    private Double oddsMin;
    @ApiModelProperty(value = "投注号")
    List<BettingNumberVo> numberVoList;
    }
