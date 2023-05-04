package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author year
 * 后台管理员白名单
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_whitelist")
@ApiModel("后台管理员白名单")
public class SysWhitelist implements Serializable {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("ip白名单")
    private String ip;

    @ApiModelProperty("状态 1/true:启用 0/false:禁用")
    private Boolean status;

    @ApiModelProperty("备注")
    private String remark;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("最后更新时间")
    private Date updateTime;

    @ApiModelProperty("最后更新人")
    private String updateBy;

}
