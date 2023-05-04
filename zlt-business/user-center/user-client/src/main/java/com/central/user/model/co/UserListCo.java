package com.central.user.model.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UserListCo extends PageCo {

    @ApiModelProperty(value = "会员id")
    private String userId;

    @ApiModelProperty(value = "会员账号/三方编号")
    private String username;


    @ApiModelProperty(value = "最后登录时间")
    private String lastLoginTime;

    @ApiModelProperty(value = "三方平台")
    private String merchantName;

    @ApiModelProperty(value = "货币")
    private String currency;

    @ApiModelProperty(value = "状态")
    private Integer onlineStatus;

    private String type;


}
