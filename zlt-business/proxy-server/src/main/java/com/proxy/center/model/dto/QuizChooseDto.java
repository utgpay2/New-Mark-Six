package com.proxy.center.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QuizChooseDto {

    @ApiModelProperty(value = "商户彩种分类(四类)id")
    private Long id;
    @ApiModelProperty(value = "商户彩种分类(三类)ID")
    private Long quizDetailsId;
    @ApiModelProperty(value = "商户彩种分类(二类)ID")
    private Long quizId;
    @ApiModelProperty(value = "商户id")
    private Long siteId;
    @ApiModelProperty(value = "商户彩种ID")
    private Long siteLotteryId;
    @ApiModelProperty(value = "商户彩种分类(一类)ID")
    private Long siteCategoryId;

}
