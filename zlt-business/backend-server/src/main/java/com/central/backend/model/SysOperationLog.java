package com.central.backend.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 操作日志表
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("sys_operation_log")
public class SysOperationLog extends SuperEntity {

    @ApiModelProperty(value = "操作人id")
    private Long userId;

    @ApiModelProperty(value = "操作人姓名(账号)")
    private String userName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "IP")
    private String userIp;


    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    @ApiModelProperty(value = "操作内容")
    private String operationEvent;
}
