package com.central.backend.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QuizDetailsDto {

    @ApiModelProperty(value = "站点彩种分类(三类)id")
    private Long id;
    @ApiModelProperty(value = "站点彩种分类(二类)ID")
    private Long quizId;
    @ApiModelProperty(value = "站点id")
    private Long siteId;
    @ApiModelProperty(value = "站点彩种ID")
    private Long siteLotteryId;
    @ApiModelProperty(value = "站点彩种分类(一类)ID")
    private Long siteCategoryId;

}
