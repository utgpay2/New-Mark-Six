package com.central.user.model.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SysUserListCo extends PageCo {

    @ApiModelProperty(value = "会员账号")
    private String username;

    @ApiModelProperty(value = "登录ip")
    private String loginIp;

    @ApiModelProperty(value = "最后登录时间-开始")
    private String lastLoginTimeStart;

    @ApiModelProperty(value = "最后登录时间-结束")
    private String lastLoginTimeEnd;


    @ApiModelProperty(value = "权限名称")
    private String roleId;

    private String type;



}
