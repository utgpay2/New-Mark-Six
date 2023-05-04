package com.central.backend.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yixiu
 * 用户实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysAdminUserEnabledDto {
    /**
     * 管理员ID
     */
    @ApiModelProperty("管理员ID")
    private Long id;
    @ApiModelProperty(value = "状态：0/false.禁用，1/true.启用")
    private Boolean enabled;

}
