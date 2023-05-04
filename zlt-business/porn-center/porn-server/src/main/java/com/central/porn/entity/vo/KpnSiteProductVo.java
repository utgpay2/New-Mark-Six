package com.central.porn.entity.vo;

import com.central.common.vo.LanguageNameMulti;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("站点产品")
public class KpnSiteProductVo extends LanguageNameMulti implements Serializable {
    @ApiModelProperty(value = "产品id")
    private Long id;
    @ApiModelProperty(value = "名称(多语言)")
    private String name;
    @ApiModelProperty(value = "价格(K币)")
    private BigDecimal price;
    @ApiModelProperty(value = "价格(现金)")
    private BigDecimal realPrice;
    @ApiModelProperty(value = "货币")
    private String currency;

//    @ApiModelProperty(value = "频道名称(中文)")
//    private String nameZh;
//
//    @ApiModelProperty(value = "频道名称(英文)")
//    private String nameEn;
//
//    @ApiModelProperty(value = "频道名称(柬文)")
//    private String nameKh;

    @ApiModelProperty(value = "排序(越大越靠前)")
    private Integer sort;
}
