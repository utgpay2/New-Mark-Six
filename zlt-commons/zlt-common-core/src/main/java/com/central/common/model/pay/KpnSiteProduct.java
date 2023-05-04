package com.central.common.model.pay;

import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * vip产品
 *
 * @author yixiu
 * @date 2023-02-03 19:35:22
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_product")
public class KpnSiteProduct extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "站点id")
    private Long siteId;
    @ApiModelProperty(value = "站点编码")
    private String siteCode;
    @ApiModelProperty(value = "站点名称")
    private String siteName;
    @ApiModelProperty(value = "产品名称(中文)")
    private String nameZh;
    @ApiModelProperty(value = "产品名称(英文)")
    private String nameEn;
    @ApiModelProperty(value = "产品名称(柬文)")
    private String nameKh;
    @ApiModelProperty(value = "价格(K币)")
    private BigDecimal price;
    @ApiModelProperty(value = "实际价格(K币)")
    private BigDecimal realPrice;
    @ApiModelProperty(value = "有效期(天数)")
    private Integer validDays;
    @ApiModelProperty(value = "状态 0下架,1上架")
    private Integer status;
    @ApiModelProperty(value = "优惠描述")
    private String discountRemark;
    @ApiModelProperty(value = "排序(越大越靠前)")
    private Integer sort;
    }
