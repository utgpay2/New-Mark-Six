package com.central.backend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@ApiModel("语言包")
public class FullSourceVo implements Serializable {
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
