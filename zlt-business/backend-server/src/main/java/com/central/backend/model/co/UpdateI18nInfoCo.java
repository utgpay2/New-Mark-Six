package com.central.backend.model.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel
@Data
public class UpdateI18nInfoCo extends SaveI18nInfoCo{

    @ApiModelProperty(value = "主键", required = true)
    @NotNull(message = "主键不能为空", groups = {Update.class})
    private Long id;

    @ApiModelProperty(value = "所属 0=前台PC，1=后台 2=前台移动端 3=前台错误消息 4=后台错误消息")
    @NotNull(message = "fromOf不能为空", groups = {Update.class})
    private Integer fromOf;

}
