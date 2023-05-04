package com.central.backend.vo;

import com.central.common.utils.Decimal2Serializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("会员列表")
@Data
public class UserListInfoVo {

    @ApiModelProperty(value = "序号")
    private String id;

    @ApiModelProperty(value = "站点平台")
    private String siteName;

    @ApiModelProperty(value = "站点code")
    private String siteCode;

    @ApiModelProperty(value = "会员账号")
    private String userName;

    @ApiModelProperty(value = "货币")
    private String currency;

    @ApiModelProperty(value = "k余额")
    @JsonSerialize(using = Decimal2Serializer.class, nullsUsing = Decimal2Serializer.class)
    private BigDecimal money;

    @ApiModelProperty(value = "状态 1:在线，2：下线")
    private Integer onlineStatus;

    @ApiModelProperty(value = "最后登录时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String lastLoginTime;

    @ApiModelProperty(value = "最后登录ip")
    private String loginIp;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "开户时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String createTime;
}
