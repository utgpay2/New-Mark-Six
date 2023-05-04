package com.central.porn.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("影片详细信息")
public class KpnMovieVo extends KpnSiteMovieBaseVo implements Serializable {

    @ApiModelProperty("影片url")
    private String url;

    @ApiModelProperty("演员id")
    private Long actorId;

//    @ApiModelProperty("付费类型 0/false:免费,1/true:付费")
//    private Boolean payType;

    @ApiModelProperty("影片收藏状态 false未收藏,true已收藏")
    private Boolean hasFavor = false;

}
