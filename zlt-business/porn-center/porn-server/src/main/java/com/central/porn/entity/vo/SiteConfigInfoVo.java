package com.central.porn.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("站点配置信息")
public class SiteConfigInfoVo implements Serializable {

    @ApiModelProperty("线路信息")
    private Map<String, List<String>> kpnLineVos;

    @ApiModelProperty(value = "试看时长(秒)")
    private Integer tryTime;

    @ApiModelProperty(value = "防丢失域名")
    private String lostDomain;
}
