package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

/**
 * 下注分类
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_category")
public class Category extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "分类名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String remark;
    @ApiModelProperty(value = "顺序")
    private Integer sort;
    }
