package com.central.backend.model.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class SaveI18nInfoCo {

    @ApiModelProperty(value = "中文")
    private String zh;

    @ApiModelProperty(value = "英文")
//    @NotNull(message = "英文不能为空", groups = {Save.class})
    private String en;

    @ApiModelProperty(value = "高棉语")
//    @NotNull(message = "高棉语不能为空", groups = {Save.class})
    private String kh;

    @ApiModelProperty(value = "泰文")
//    @NotNull(message = "泰文不能为空", groups = {Save.class})
    private String th;

    @ApiModelProperty(value = "越南语")
    private String vi;

    @ApiModelProperty(value = "马来语")
    private String my;

    @ApiModelProperty(value = "所属 0=前台PC，1=后台 2=前台移动端 3=前台错误消息 4=后台错误消息")
    private Integer fromOf;

    String operator;

    public interface Save {}

    public interface  Update {}

}
