package com.central.backend.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class EnabledUserCo {

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "是否启用(状态：0.禁用，1.启用)")
    private Boolean enabled;


    private String updateBy;

}
