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
public class KpnSiteMovieStatusDto {
    @ApiModelProperty(value = "站点影片ID")
    private Long id;
    @ApiModelProperty(value = "状态 0待发布,1上架,2下架")
    private Integer status;
}
