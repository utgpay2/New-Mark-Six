package com.central.backend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel("")
@Data
public class CategoryVo {

    @ApiModelProperty(value = "标签分类id")
    private Long categoryId;
    @ApiModelProperty(value = "标签分类名称")
    private String categoryName;
}
