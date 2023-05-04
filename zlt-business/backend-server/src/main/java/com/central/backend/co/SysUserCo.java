package com.central.backend.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SysUserCo extends PageCo {


    @ApiModelProperty(value = "站点id",required = true)
    private Long siteId;

    @ApiModelProperty(value = "会员账号")
    private String userName;
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty("会员等级 0/false:普通会员,1/true:vip")
    private Boolean vip;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

}
