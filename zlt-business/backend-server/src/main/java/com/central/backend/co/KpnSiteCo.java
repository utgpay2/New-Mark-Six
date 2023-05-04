package com.central.backend.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class KpnSiteCo extends PageCo {


    @ApiModelProperty(value = "站点名称")
    private String name;


    @ApiModelProperty(value = "站点状态 0/false:关闭,1/true:开启")
    private Boolean status;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

}
