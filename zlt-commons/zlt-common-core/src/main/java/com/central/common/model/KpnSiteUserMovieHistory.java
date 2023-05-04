package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_user_movie_history")
@ApiModel("会员影片浏览历史表")
public class KpnSiteUserMovieHistory implements Serializable {

    @ApiModelProperty("会员id")
    private Long userId;

    @ApiModelProperty(value = "影片id")
    private Long movieId;

    @ApiModelProperty(value = "观看时间")
    private Date createTime;
}
