package com.central.backend.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class MovieVo {

    @ApiModelProperty(value = "影片id")
    private Long movieId;

    @ApiModelProperty(value = "影片名称-中文")
    private String nameZh;

    @ApiModelProperty(value = "影片名称-英文")
    private String nameEn;

    @ApiModelProperty(value = "影片名称-柬文")
    private String nameKh;

    @ApiModelProperty(value = "封面地址")
    private String coverUrl;

    @ApiModelProperty(value = "状态 0待发布,1上架,2下架")
    private Integer status;

}
