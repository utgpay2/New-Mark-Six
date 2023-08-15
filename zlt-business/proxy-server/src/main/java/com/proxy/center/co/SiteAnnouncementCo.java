package com.proxy.center.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SiteAnnouncementCo extends PageCo {


    @ApiModelProperty(value = "商户id",required = true)
    private Long siteId;

    @ApiModelProperty(value = "操作人")
    private String operator;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "开始时间")
    private String startTime;


    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
