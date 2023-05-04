package com.central.backend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel("")
@Data
public class OnlineTrendVo {

    @ApiModelProperty(value = "人数")
    private Integer onlineNumber;

    @ApiModelProperty(value = "时间")
    private String date;

}
