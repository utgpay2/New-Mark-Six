package com.central.user.model.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel
public class SysTansterMoneyPageCo extends PageCo {

    @ApiModelProperty(value = "会员账号")
    private String userName;

    @ApiModelProperty(value = "会员Id")
    private Long userId;

    @ApiModelProperty(value = "1:转入,2:转出,3:派彩,4:下注,5:手动入款,6:手动出款")
    private String[] orderType;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "父级")
    private String parent;

    @ApiModelProperty(value = "第三方交易编号")
    private String traceId;

    @ApiModelProperty(value = "日期类型：0.今天，1.本周，2.上周")
    private String type;

}
