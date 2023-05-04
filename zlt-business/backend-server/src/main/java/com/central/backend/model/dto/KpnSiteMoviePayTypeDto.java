package com.central.backend.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("站点影片")
public class KpnSiteMoviePayTypeDto {
    @ApiModelProperty(value = "站点影片ID")
    private Long id;
    @ApiModelProperty("付费类型 0/false:免费,1/true:付费")
    private Boolean payType;
}
