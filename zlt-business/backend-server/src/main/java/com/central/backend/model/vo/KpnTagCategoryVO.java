package com.central.backend.model.vo;

import com.central.common.model.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 影片标签分类
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class KpnTagCategoryVO{
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "分类ID")
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
    @ApiModelProperty(value = "影片数量")
    private Integer total;
    }
