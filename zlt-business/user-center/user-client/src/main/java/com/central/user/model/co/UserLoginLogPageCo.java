package com.central.user.model.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UserLoginLogPageCo extends PageCo {

    @ApiModelProperty(value = "用户帐号")
    private String userName;

    @ApiModelProperty(value = "登录IP")
    private String loginIp;

    @ApiModelProperty(value = "是否模糊查询(0:不勾选 1:勾选)")
    private Integer isOpen;

    @ApiModelProperty(value = "状态：0.禁用，1.启用")
    private Integer enabled;

}
