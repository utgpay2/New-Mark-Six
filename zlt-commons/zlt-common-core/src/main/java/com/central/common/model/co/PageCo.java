package com.central.common.model.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class PageCo {

    @ApiModelProperty(value = "分页起始位置", required = true)
    @NotNull(message = "分页起始位置不能为空")
    private Integer page;

    @ApiModelProperty(value = "分页结束位置", required = true)
    @NotNull(message = "分页结束位置不能为空")
    private Integer limit;

}
