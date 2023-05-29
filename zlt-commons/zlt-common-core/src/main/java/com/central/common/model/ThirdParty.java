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
@TableName("mks_third_party")
@ApiModel("站点三方密钥")
public class ThirdParty extends SuperEntity{

    @ApiModelProperty(value = "站点编码")
    private String siteCode;

    @ApiModelProperty(value = "三方密钥")
    private String secretKey;

    @ApiModelProperty(value = "站点id")
    private Long siteId;
}
