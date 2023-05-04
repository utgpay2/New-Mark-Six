package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_sign_detail")
public class KpnSiteSignDetail implements Serializable {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("站点id")
    private Long siteId;
    @ApiModelProperty("站点code")
    private String siteCode;
    @ApiModelProperty("站点名称")
    private String siteName;
    @ApiModelProperty("会员id")
    private Long userId;
    @ApiModelProperty("会员账号")
    private String username;
    @ApiModelProperty("累计签到天数")
    private Integer days;
    @ApiModelProperty("签到月份")
    private String signMonth;
    @ApiModelProperty("签到日期")
    private String signDate;
    @ApiModelProperty("是否得到奖励(0/false:未得到,1/true:获取到)")
    private Boolean isReward;
    @ApiModelProperty("站点签到规则id")
    private Long siteSignId;
    @ApiModelProperty("奖励vip天数")
    private Integer rewardVip;
    @ApiModelProperty("奖励k币数")
    private BigDecimal rewardKb;
    @ApiModelProperty("创建日期")
    private Date createTime;
}
