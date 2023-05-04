package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_user_actor_favorites")
@ApiModel("会员收藏演员表")
public class KpnSiteUserActorFavorites implements Serializable {

    @ApiModelProperty("会员id")
    private Long userId;

    @ApiModelProperty(value = "演员id")
    private Long actorId;

    @ApiModelProperty(value = "收藏时间")
    private Date createTime;
}
