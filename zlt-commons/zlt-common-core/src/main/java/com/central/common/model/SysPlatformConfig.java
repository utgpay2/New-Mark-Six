package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;


/**
 * 系统配置实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_platform_config")
@ApiModel("系统配置实体")
public class SysPlatformConfig  {

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "网站维修状态(0:停用,1:启用)")
	private Boolean repairState;

	@ApiModelProperty(value = "网站维修通告链接")
	private String repairUrlAddress;

}
