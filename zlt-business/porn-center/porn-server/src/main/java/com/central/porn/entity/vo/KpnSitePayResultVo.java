package com.central.porn.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("支付信息")
public class KpnSitePayResultVo implements Serializable {

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty
    private List<KpnSiteBankCardPayVo> bankCardPayVos;
}
