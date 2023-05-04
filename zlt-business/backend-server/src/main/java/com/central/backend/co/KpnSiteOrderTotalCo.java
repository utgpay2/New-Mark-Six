package com.central.backend.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class KpnSiteOrderTotalCo {

    @ApiModelProperty(value = "站点id",required = true)
    private Long siteId;
    @ApiModelProperty(value = "会员账号")
    private String userName;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "创建时间-开始")
    private String startTime;

    @ApiModelProperty(value = "创建时间-结束")
    private String endTime;



    @ApiModelProperty(value = "状态 0待审核,1审核通过,2审核拒绝")
    private Integer status;

}
