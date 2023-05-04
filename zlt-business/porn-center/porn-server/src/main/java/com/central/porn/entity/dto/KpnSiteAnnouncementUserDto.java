package com.central.porn.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户是否已读站点公告")
@Data
public class KpnSiteAnnouncementUserDto {
    @ApiModelProperty(value = "公告id")
    private Long annId;
    @ApiModelProperty(value = "状态 0未读,1已读")
    private Integer isRead;
}
