package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("rpt_site_search_total")
@ApiModel("站点关键词总搜索量")
public class RptSiteSearchTotal implements Serializable {

    @ApiModelProperty("站点id")
    private Long siteId;

    @ApiModelProperty("搜索词")
    private String keywords;

    @ApiModelProperty("搜索次数")
    private Long number;
}
