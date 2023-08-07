package com.central.backend.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QuizDto {

    @ApiModelProperty(value = "商户彩种分类(二类)id")
    private Long id;
    @ApiModelProperty(value = "商户id")
    private Long siteId;
    @ApiModelProperty(value = "商户彩种ID")
    private Long siteLotteryId;
    @ApiModelProperty(value = "商户彩种分类(一类)ID")
    private Long siteCategoryId;

}
