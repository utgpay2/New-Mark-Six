package com.central.backend.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class KpnSiteSuggestionUpdateCo {


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "状态 0待处理,1已处理")
    private Integer status;


    private String updateBy;
}
