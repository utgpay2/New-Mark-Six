package com.central.backend.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SysWhiteListCo extends PageCo {

    @ApiModelProperty("ip白名单")
    private String ip;

    @ApiModelProperty("状态 1/true:启用 0/false:禁用")
    private Boolean status;

}
