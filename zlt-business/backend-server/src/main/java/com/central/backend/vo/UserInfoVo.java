package com.central.backend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户基本信息")
@Data
public class UserInfoVo {

    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "头像地址")
    private String headImgUrl;
    @ApiModelProperty(value = "投注自动提交 开启：true,关闭：false")
    private Boolean isAutoBet;
    @ApiModelProperty(value = "账号类型：APP：前端app用户，APP_GUEST：前端app游客用户，BACKEND：后端管理用户")
    private String type;
}
