package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
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
@TableName("kpn_tag_category")
public class KpnTagCategory extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "分类名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String remark;
    }
