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
@TableName("mks_site")
@ApiModel("商户信息")
public class Site extends SuperEntity{

    @ApiModelProperty(value = "商户编码")
    private String code;

    @ApiModelProperty(value = "商户名称")
    private String name;

    @ApiModelProperty(value = "商户logo地址")
    private String logoUrl;

    @ApiModelProperty(value = "域名")
    private String domains;

    @ApiModelProperty(value = "商户状态 0关闭,1开启")
    private Boolean status;

    @ApiModelProperty(value = "商户维护状态 0未维护,1维护中")
    private Boolean repairStatus;

    @ApiModelProperty(value = "币种编码")
    private String currencyCode;

    @ApiModelProperty(value = "会员数")
    private Long memberNum;

    @ApiModelProperty(value = "VIP数")
    private Long vipNum;
}
