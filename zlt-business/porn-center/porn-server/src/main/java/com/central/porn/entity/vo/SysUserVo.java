package com.central.porn.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("会员信息")
public class SysUserVo implements Serializable {

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("头像")
    private String headImgUrl;

    @ApiModelProperty("邀请码")
    private String inviteCode;

    @ApiModelProperty("我的推广码")
    private String promotionCode;

    @ApiModelProperty("是否VIP")
    private Boolean vip;

    @ApiModelProperty("vip到期时间")
    private String vipExpire;

    @ApiModelProperty("剩余天数")
    private Long between;

    @ApiModelProperty("k币余额")
    private BigDecimal kBalance;

}
