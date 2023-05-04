package com.central.porn.entity.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class MemberChannelSortCo {
    @ApiModelProperty("频道id")
    private Long channelId;
    @ApiModelProperty("排序")
    private Long sort;
}
