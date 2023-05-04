package com.central.backend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel("推广列表")
@Data
public class UserExtensionListInfoVo {


    @ApiModelProperty("上级账号")
    private String parentName;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
