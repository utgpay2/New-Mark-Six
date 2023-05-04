package com.central.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LanguageNameMulti implements Serializable {

    //    @JsonIgnore
    @ApiModelProperty(value = "名称(中文)")
    public String nameZh;
    //
//    @JsonIgnore
    @ApiModelProperty(value = "名称(英文)")
    public String nameEn;
    //
//    @JsonIgnore
    @ApiModelProperty(value = "名称(柬文)")
    public String nameKh;

    public void setNull() {
        this.nameEn = null;
        this.nameKh = null;
        this.nameZh = null;
    }
}
