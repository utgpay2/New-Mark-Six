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
@TableName("kpn_site_topic_composing")
@ApiModel("专题排版表")
public class KpnSiteTopicComposing {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "缩略图")
    private String image;

    @ApiModelProperty(value = "备注")
    private String remark;


}
