package com.central.backend.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class KpnSiteLoginLogCo extends PageCo {


    @ApiModelProperty(value = "站点id",required = true)
    private Long siteId;

    @ApiModelProperty(value = "会员账号")
    private String userName;

    @ApiModelProperty(value = "ip")
    private String loginIp;

    @ApiModelProperty(value = "登录时间-开始")
    private String startTime;

    @ApiModelProperty(value = "登录时间-结束")
    private String endTime;

}
