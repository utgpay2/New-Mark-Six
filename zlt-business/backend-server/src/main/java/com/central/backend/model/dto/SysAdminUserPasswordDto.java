package com.central.backend.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zlt
 * 用户实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysAdminUserPasswordDto {
    /**
     * 管理员ID
     */
    @ApiModelProperty("管理员ID")
    private Long id;
    @ApiModelProperty("旧密码")
    private String oldPassword;
    @ApiModelProperty("新密码")
    private String newPassword;

}
