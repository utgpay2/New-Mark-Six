package com.central.backend.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
public class UserVipExpireVo {


    @ApiModelProperty("原有vip到期日期")
    private Date beforeExpire;

    @ApiModelProperty("变动后vip到期日期")
    private Date afterExpire;
}
