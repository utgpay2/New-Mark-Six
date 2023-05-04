package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_line")
@ApiModel("线路配置")
public class KpnLine extends SuperEntity{

    @ApiModelProperty(value = "线路 1:线路一,2:线路二,3:线路三")
    private Integer line;

    @ApiModelProperty(value = "域名")
    private String domain;


    @ApiModelProperty(value = "状态 0:禁用,1:启用")
    private Integer status;


}
