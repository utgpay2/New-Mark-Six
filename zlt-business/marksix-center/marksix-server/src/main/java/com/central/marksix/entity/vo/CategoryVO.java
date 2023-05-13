package com.central.marksix.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 下注分类
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CategoryVO {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "站点分类ID")
    private Long id;
    @ApiModelProperty(value = "分类名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String remark;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    @ApiModelProperty(value = "站点彩种ID")
    private Integer siteLotteryId;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "下注分类ID")
    private Integer categoryId;
    }
