package com.central.common.model;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("rpt_site_movie_date")
@ApiModel("站点影片日报表")
public class RptSiteMovieDate implements Serializable {

    @ApiModelProperty(value = "站点id")
    private Long siteId;

    @ApiModelProperty(value = "影片id")
    private Long movieId;

    @ApiModelProperty(value = "统计日期 yyyy-MM-dd")
    private String date;

    @ApiModelProperty(value = "影片站点播放量")
    private Long vv;

    @ApiModelProperty(value = "影片站点收藏量")
    private Long favorites;
}
