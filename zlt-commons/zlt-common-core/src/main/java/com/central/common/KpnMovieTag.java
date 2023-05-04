package com.central.common;

import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 影片标签关联
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_movie_tag")
public class KpnMovieTag implements Serializable {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("影片id")
    private Long movieId;

    @ApiModelProperty("标签分类id")
    private Long tagCategoryId;

    @ApiModelProperty("标签id")
    private Long tagId;
}
