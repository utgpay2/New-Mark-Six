package com.central.marksix.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zlt
 * 用户登录实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginDto {

    @ApiModelProperty(value = "商户编码")
    String siteCode;

    @ApiModelProperty(value = "随机字符串")
    String random;

    @ApiModelProperty(value = "签名摘要")
    String sign;

    @ApiModelProperty(value = "登录账号")
    String username
            ;
    @ApiModelProperty(value = "平台类型，H5为1, PC为2")
    Integer platformType;
    @ApiModelProperty(value = "来源 app 或 web")
    String source;

    @ApiModelProperty(value = "密码")
    String password;

    @ApiModelProperty(value = "代理用户名")
    Integer lotteryId;


}
