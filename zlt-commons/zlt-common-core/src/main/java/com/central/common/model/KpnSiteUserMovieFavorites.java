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
@TableName("kpn_site_user_movie_favorites")
@ApiModel("会员收藏影片表")
public class KpnSiteUserMovieFavorites implements Serializable {

    @ApiModelProperty("会员id")
    private Long userId;

    @ApiModelProperty(value = "影片id")
    private Long movieId;

    @ApiModelProperty(value = "收藏时间")
    private Date createTime;
}
