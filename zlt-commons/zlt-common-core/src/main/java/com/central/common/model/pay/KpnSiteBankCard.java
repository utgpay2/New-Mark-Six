package com.central.common.model.pay;

import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 收款银行卡配置
 *
 * @author yixiu
 * @date 2023-02-03 19:35:22
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_bank_card")
public class KpnSiteBankCard extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "站点id")
    private Integer siteId;
    @ApiModelProperty(value = "站点编码")
    private String siteCode;
    @ApiModelProperty(value = "站点名称")
    private String siteName;
    @ApiModelProperty(value = "所属银行id")
    private Long bankId;
    @ApiModelProperty(value = "银行名称")
    private String bankName;
    @ApiModelProperty(value = "开户姓名")
    private String account;
    @ApiModelProperty(value = "卡号")
    private String card;
    @ApiModelProperty(value = "排序(越大越靠前)")
    private Integer sort;
    @ApiModelProperty(value = "状态 0下架,1上架")
    private Integer status;
    }
