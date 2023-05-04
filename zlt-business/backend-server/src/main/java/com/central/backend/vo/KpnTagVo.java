package com.central.backend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel("")
@Data
public class KpnTagVo {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "标签名称(中文)")
    private String nameZh;
    @ApiModelProperty(value = "标签名称(英文)")
    private String nameEn;
    @ApiModelProperty(value = "标签名称(柬文)")
    private String nameKh;


    @ApiModelProperty(value = "影片数量")
    private Integer kpnTagNum;
}
