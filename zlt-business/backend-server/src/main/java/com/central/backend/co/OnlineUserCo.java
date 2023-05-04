package com.central.backend.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("在线用户查询参数")
@Data
public class OnlineUserCo extends PageCo {

    @ApiModelProperty(value = "注册起始时间查询")
    private Date start;

    @ApiModelProperty(value = "注册结束时间查询")
    private Date endTime;

    @ApiModelProperty(value = "tag:0 当日 tag:1 当月")
    private Integer tag;
}
