package com.central.porn.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("站点影片搜索信息")
public class KpnSiteSearchVo implements Serializable {

    @ApiModelProperty("关键词")
    private String keywords;

    @ApiModelProperty("搜索量")
    private Long number;
}
