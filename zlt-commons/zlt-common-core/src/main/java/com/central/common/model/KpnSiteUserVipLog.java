package com.central.common.model;

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
@ApiModel("vip变动日志")
public class KpnSiteUserVipLog implements Serializable {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("站点id")
    private Long siteId;
    @ApiModelProperty("站点编码")
    private String siteCode;
    @ApiModelProperty("站点名称")
    private String siteName;
    @ApiModelProperty("会员id")
    private Long userId;
    @ApiModelProperty("会员账号")
    private String username;
    @ApiModelProperty("类型 1:签到,2:填写邀请码,3:推广,4:k币兑换,5:金额购买")
    private Integer type;
    @ApiModelProperty("K币/金额")
    private BigDecimal amount;
    @ApiModelProperty("金额货币")
    private String currencyCode;
    @ApiModelProperty("原有vip到期日期(yyyy-MM-dd)")
    private Date beforeExpire;
    @ApiModelProperty("变动天数")
    private Integer days;
    @ApiModelProperty("变动后vip到期日期(yyyy-MM-dd)")
    private Date afterExpire;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("创建人")
    private String createBy;
}
