package com.central.backend.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @author zlt
 * 用户实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysAdminUserDto {
    /**
     * 管理员ID
     */
    @ApiModelProperty("管理员ID")
    private Long id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty("商户id")
    private Long siteId;
    @ApiModelProperty("商户编码")
    private String siteCode;
    @ApiModelProperty("商户名称")
    private String siteName;
    @ApiModelProperty(value = "状态：0/false.禁用，1/true.启用")
    private Boolean enabled;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "角色ID")
    private Set<Long> roleIds;

}
