package com.central.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("国际化翻译位置")
@Data
public class I18nPositionVO {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

}
