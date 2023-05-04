package com.central.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@ApiModel("国际化资源DTO")
@Data
public class I18nSourceDTO implements Serializable {

    private static final long serialVersionUID = 448547528757858758L;

    @ApiModelProperty("中文国际化资源")
    private Map<String, String> zh;

    @ApiModelProperty("英文国际化资源")
    private Map<String, String> en;

    @ApiModelProperty("高棉语国际化资源")
    private Map<String, String> kh;

    @ApiModelProperty("泰语国际化资源")
    private Map<String, String> th;

    @ApiModelProperty("越南语国际化资源")
    private Map<String, String> vi;

    @ApiModelProperty("马来国际化资源")
    private Map<String, String> my;

}
