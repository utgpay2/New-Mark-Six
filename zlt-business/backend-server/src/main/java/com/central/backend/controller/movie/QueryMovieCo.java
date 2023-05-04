package com.central.backend.controller.movie;

import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("影片搜索")
public class QueryMovieCo extends PageCo implements Serializable {

    @ApiModelProperty("站点id")
    private Long siteId;

    @ApiModelProperty("搜索条件 0:影片名,1:影片ID,2:演员名")
    private Integer searchId;

    @ApiModelProperty("搜索条件内容")
    private String search;

    @ApiModelProperty("更新时间(开始时间)")
    private String startTime;

    @ApiModelProperty("更新时间(结束时间)")
    private String endTime;

    @ApiModelProperty("状态(-1:全部,0:待发布,1:上架,2:下架)")
    private Integer status;

    @ApiModelProperty("国家(-1:全部,0:日本,1:中国大陆,2:中国台湾,3:韩国,4:欧美,5:东南亚,6:其他地区)")
    private Integer country;

    @ApiModelProperty("标签分类id(-1:全部)")
    private Integer tagCategoryId;

    @ApiModelProperty("标签id(-1:全部)")
    private Integer tagId;

    @ApiModelProperty("影片类型(-1:全部,0:无码,1:有码)")
    private Integer type;

    @ApiModelProperty("拍摄性质(-1:全部,0:专业拍摄,1:偷拍,2:自拍,3:其他)")
    private Integer shooting;

    @ApiModelProperty("字幕类型(-1:全部,0:无字幕,1:中文字幕,2:英文字幕,3:中英文字幕,4:其他字幕)")
    private Integer subtitle;

    @ApiModelProperty("付费类型(-1:全部,0:免费,1:vip)")
    private Integer payType;

    @ApiModelProperty("排序 0:正序,1:倒序(默认)")
    private Integer sort = 1;

}



























