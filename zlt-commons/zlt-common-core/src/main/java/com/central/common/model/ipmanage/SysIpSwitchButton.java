package com.central.common.model.ipmanage;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 *
 * @author yixiu
 * @date 2023-02-03 15:07:56
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("ip_switch_button")
public class SysIpSwitchButton{
    private static final long serialVersionUID=1L;
    @TableId
    private Integer id;
    @ApiModelProperty(value = "白名单开关1打开0关闭")
    private Integer whiteipSwithcButton;
    @ApiModelProperty(value = "黑名单开关1打开0关闭")
    private Integer blackipSwithcButton;
    }
