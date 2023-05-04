package com.central.backend.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class KpnSiteOrderUpdateCo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "状态 0待审核,1审核通过,2审核拒绝")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    private String updateBy;
}
