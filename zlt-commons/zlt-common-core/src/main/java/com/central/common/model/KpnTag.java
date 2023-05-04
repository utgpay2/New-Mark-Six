package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 影片标签
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_tag")
public class KpnTag extends SuperEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "标签分类id")
    private Long categoryId;
    @ApiModelProperty(value = "标签分类名称")
    private String categoryName;
    @ApiModelProperty(value = "标签名称(中文)")
    private String nameZh;
    @ApiModelProperty(value = "标签名称(英文)")
    private String nameEn;
    @ApiModelProperty(value = "标签名称(柬文)")
    private String nameKh;
    @ApiModelProperty(value = "标签总播放量")
    private Long vv;
    @ApiModelProperty(value = "描述")
    private String remark;
}
