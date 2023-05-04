package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_topic_movie")
@ApiModel("站点专题电影关联")
public class KpnSiteTopicMovie extends SuperEntity {
    @ApiModelProperty(value = "站点id")
    private Long siteId;

    @ApiModelProperty(value = "站点编码")
    private String siteCode;

    @ApiModelProperty(value = "站点名称")
    private String siteName;

    @ApiModelProperty(value = "专题id")
    private Long topicId;

    @ApiModelProperty(value = "电影id")
    private Long movieId;

    @ApiModelProperty(value = "专题影片播放量")
    private Long vv;

    @ApiModelProperty(value = "专题影片排序(只在首页有效)")
    private Long sort;
}
