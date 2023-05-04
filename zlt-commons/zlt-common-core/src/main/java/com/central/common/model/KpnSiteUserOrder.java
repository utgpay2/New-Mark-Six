package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_user_order")
@ApiModel("站点充值订单")
public class KpnSiteUserOrder extends SuperEntity {

    @ApiModelProperty(value = "站点id")
    private Long siteId;

    @ApiModelProperty(value = "站点编码")
    private String siteCode;

    @ApiModelProperty(value = "站点名称")
    private String siteName;

    @ApiModelProperty(value = "内部订单号")
    private String orderNo;


    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "汇款人姓名")
    private String remitterName;

    @ApiModelProperty(value = "收款银行id")
    private Long bankId;
    @ApiModelProperty(value = "收款银行")
    private String bankName;

    @ApiModelProperty(value = "收款卡id")
    private Long bankCardId;

    @ApiModelProperty(value = "收款卡号")
    private String bankCard;

    @ApiModelProperty(value = "收款卡开户名")
    private String bankCardAccount;

    @ApiModelProperty(value = "产品id")
    private Long productId;
    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "手机号码")
    private String mobile;


    @ApiModelProperty(value = "货币价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "银行交易号后6位")
    private String certificate;

    @ApiModelProperty(value = "备注")
    private String remark;



    @ApiModelProperty(value = "状态 0待审核,1审核通过,2审核拒绝")
    private Integer status;
}
