package com.central.porn.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("线路")
public class KpnLineVo implements Serializable {

    @ApiModelProperty("线路名(多语言)")
    private String name;

    @ApiModelProperty("域名")
    private String domain;

}
