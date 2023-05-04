package com.central.backend.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class KpnSiteTopicUpdateCo {

    @ApiModelProperty(value = "id")
    private Long id;


    @ApiModelProperty(value = "状态 0待发布,1上架,2下架")
    private Integer status;

    private String updateBy;
}
