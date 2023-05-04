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
@ApiModel("签到历史")
public class KpnSiteUserSignHistoryVo implements Serializable {

    @ApiModelProperty("日期")
    private String date;

    @ApiModelProperty("是否签到")
    private Boolean signed;
}
