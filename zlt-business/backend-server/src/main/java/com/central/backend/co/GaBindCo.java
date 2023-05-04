package com.central.backend.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class GaBindCo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "gaBind")
    private Integer gaBind;

}
