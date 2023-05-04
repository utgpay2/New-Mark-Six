package com.central.user.model.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("额度记录入参")
public class SysUserMoneyPageCo extends PageCo {

    @ApiModelProperty(value = "日期类型：0.今天，1.本周，2.上周", required = true)
    private Integer type;

    @ApiModelProperty(hidden = true)
    private String startTime;

    @ApiModelProperty(hidden = true)
    private String endTime;

    @ApiModelProperty(hidden = true)
    private Long userId;

}
