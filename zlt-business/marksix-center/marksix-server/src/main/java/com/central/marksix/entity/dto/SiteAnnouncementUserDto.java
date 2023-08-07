package com.central.marksix.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户是否已读商户公告")
@Data
public class SiteAnnouncementUserDto {
    @ApiModelProperty(value = "公告id")
    private Long annId;
    @ApiModelProperty(value = "状态 0未读,1已读")
    private Integer isRead;
}
