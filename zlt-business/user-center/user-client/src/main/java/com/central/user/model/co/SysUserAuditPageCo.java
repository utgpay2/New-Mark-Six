package com.central.user.model.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SysUserAuditPageCo extends PageCo {

    @ApiModelProperty(value = "会员账号")
    private String userName;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "稽核类型，1：手动入款")
    private Integer auditType;

    @ApiModelProperty(value = "日期类型：0.今天，1.本周，2.上周")
    private String type;
}
