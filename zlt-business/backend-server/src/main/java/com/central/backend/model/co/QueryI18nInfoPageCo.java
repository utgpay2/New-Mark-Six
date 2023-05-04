package com.central.backend.model.co;

import com.central.common.language.LanguageFromEnum;
import com.central.common.model.co.PageCo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("查询国际化字典分页参数")
@Data
public class QueryI18nInfoPageCo extends PageCo {

    @ApiModelProperty(required = true, value = "所属 0=前台PC，1=后台 2=前台移动端 3=前台错误消息 4=后台错误消息")
    private Integer from = LanguageFromEnum.BACKEND.getCode();

    @ApiModelProperty(value = "语言 0=中文 1=英文 2=高棉语 3=泰文 4=越南语 5=马来语")
    private Integer language;

    @ApiModelProperty(value = "查询文本")
    private String word;

}
