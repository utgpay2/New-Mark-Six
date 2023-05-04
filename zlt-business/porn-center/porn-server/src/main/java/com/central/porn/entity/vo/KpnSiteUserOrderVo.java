package com.central.porn.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("站点会员订单记录")
public class KpnSiteUserOrderVo implements Serializable {

    @ApiModelProperty("日期")
    private Date createTime;

    @ApiModelProperty("支付产品名称(多语言)")
    private String productName;

    @ApiModelProperty("支付金额(货币符号)")
    private String amount;

    @ApiModelProperty("支付结果(多语言)")
    private String result;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty(value = "状态 0待审核,1审核通过,2审核拒绝")
    private Integer status;

}
