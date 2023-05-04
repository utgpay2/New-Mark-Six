package com.central.porn.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("站点客服")
public class KpnSiteServeVo implements Serializable {

    @ApiModelProperty("客服平台")
    private String platform;

    @ApiModelProperty("账号")
    private String serveAccount;

    @ApiModelProperty("PC icon")
    private String pcIconUrl;

    @ApiModelProperty("App icon")
    private String appIconUrl;
}
