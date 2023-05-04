package com.central.backend.co;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class KpnSiteAdvertiseCo extends PageCo {


    @ApiModelProperty(value = "广告名称")
    private String name;

    @ApiModelProperty(value = "站点id",required = true)
    private Long siteId;


    @ApiModelProperty(value = "投放平台(H5,PC)")
    private String device;

    @ApiModelProperty(value = "广告位置 1首页轮播图,2首页平台展示,3首页专题广告,4福利,5游戏轮播图,6游戏广告")
    private Integer position;

    @ApiModelProperty(value = "状态 0/false下架,1/true上架")
    private Boolean status;

    @ApiModelProperty(value = "有效开始时间")
    private String startTime;

    @ApiModelProperty(value = "有效结束时间")
    private String endTime;

    @ApiModelProperty(value = "排序方式：1正序、2倒序")
    private String sortBy;

}
