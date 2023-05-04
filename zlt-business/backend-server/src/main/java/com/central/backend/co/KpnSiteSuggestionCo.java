package com.central.backend.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class KpnSiteSuggestionCo extends PageCo {

    @ApiModelProperty(value = "站点id",required = true)
    private String siteId;

    @ApiModelProperty(value = "反馈内容")
    private String content;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
