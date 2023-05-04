package com.central.backend.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class KpnMoneyLogCo extends PageCo {


    @ApiModelProperty(value = "站点Id",required = true)
    private Long siteId;

    @ApiModelProperty(value = "会员账号")
    private String userName;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "变动时间-开始")
    private String startTime;

    @ApiModelProperty(value = "变动时间-结束")
    private String endTime;

}
