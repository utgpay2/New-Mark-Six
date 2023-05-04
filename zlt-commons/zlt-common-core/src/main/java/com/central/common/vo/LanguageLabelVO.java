package com.central.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageLabelVO {

    @ApiModelProperty("key")
    private Integer key;

    @ApiModelProperty("英文")
    private String valueEn;

    @ApiModelProperty("展示的语种")
    private String value;

}
