package com.central.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页入参实体类
 *
 * @author zlt
 */
@Data
public class SuperPage implements Serializable {
    private static final long serialVersionUID = -275582248840137389L;

    @ApiModelProperty(value = "分页起始位置", required = true)
    private Integer page;

    @ApiModelProperty(value = "分页结束位置", required = true)
    private Integer limit;
}
