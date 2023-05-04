package com.central.backend.controller.movie;

import com.central.common.vo.LanguageNameMulti;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("标签信息")
public class TagVo extends LanguageNameMulti {

    @ApiModelProperty("标签id")
    private Long id;

    @ApiModelProperty("标签名(多语言)")
    private String name;
}
