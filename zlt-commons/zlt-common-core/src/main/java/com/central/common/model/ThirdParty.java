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
@ApiModel("商户三方密钥")
public class ThirdParty extends SuperEntity{

    @ApiModelProperty(value = "商户编码")
    private String siteCode;

    @ApiModelProperty(value = "三方密钥")
    private String secretKey;

    @ApiModelProperty(value = "商户id")
    private Long siteId;
}
