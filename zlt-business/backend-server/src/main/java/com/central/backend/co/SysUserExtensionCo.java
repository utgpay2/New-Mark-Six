package com.central.backend.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SysUserExtensionCo extends PageCo {


    @ApiModelProperty(value = "站点id",required = true)
    private  Long siteId;
    @ApiModelProperty(value = "会员账号")
    private String userName;
    @ApiModelProperty(value = "上级账号")
    private String parentName;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

}
