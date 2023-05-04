package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("sys_language")
public class SysLanguage extends SuperEntity {

    @ApiModelProperty(value = "语言code")
    private String languageCode;

    @ApiModelProperty(value = "语言名称")
    private String languageName;

    @ApiModelProperty(value = "游是否默认")
    private Integer isDefault;

    @ApiModelProperty(value = "是否启用 1:启用。0：关闭")
    private Integer status;
}
