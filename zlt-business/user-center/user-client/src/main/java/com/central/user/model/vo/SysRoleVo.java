package com.central.user.model.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zlt
 * 角色
 */
@Data
@ApiModel("角色列表")
public class SysRoleVo  {

    private Long id;
    @ApiModelProperty(value = "角色code")
    private String code;
    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "用户人数")
    private int number;

    @ApiModelProperty(value = "最后更新者")
    private String updateBy;

    private String tenantId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


}
