package com.central.backend.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
public class KpnTagVO {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "标签ID")
    private Long id;
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
    @ApiModelProperty(value = "描述")
    private String remark;
    @ApiModelProperty(value = "影片数量")
    private Integer total;
    @ApiModelProperty(value = "播放量")
    private Integer vv;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    }
