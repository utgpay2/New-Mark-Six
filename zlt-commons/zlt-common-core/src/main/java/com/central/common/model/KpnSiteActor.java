package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_actor")
@ApiModel("站点演员收藏量")
public class KpnSiteActor implements Serializable {
    @ApiModelProperty(value = "站点id")
    private Long siteId;
    @ApiModelProperty(value = "演员id")
    private Long actorId;
    @ApiModelProperty(value = "收藏量")
    private Long favorites;
}
