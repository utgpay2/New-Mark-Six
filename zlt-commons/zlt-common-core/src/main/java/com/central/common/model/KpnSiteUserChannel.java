package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_user_channel")
@ApiModel("会员频道")
public class KpnSiteUserChannel extends SuperEntity{

    @ApiModelProperty(value = "站点id")
    private Long siteId;

    @ApiModelProperty(value = "站点编码")
    private String siteCode;

    @ApiModelProperty(value = "站点名称")
    private String siteName;

    @ApiModelProperty(value = "会员id")
    private Long userId;

    @ApiModelProperty(value = "会员账号")
    private String userName;

    @ApiModelProperty(value = "站点频道id")
    private Long channelId;

    @ApiModelProperty(value = "排序(越大越靠前)")
    private Long sort;
}
