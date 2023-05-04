package com.central.common.model.pay;

import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 收款银行渠道
 *
 * @author yixiu
 * @date 2023-02-03 19:35:22
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_bank")
public class KpnSiteBank extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "站点id")
    private Integer siteId;
    @ApiModelProperty(value = "站点编码")
    private String siteCode;
    @ApiModelProperty(value = "站点名称")
    private String siteName;
    @ApiModelProperty(value = "银行名称")
    private String name;
    @ApiModelProperty(value = "logo地址")
    private String logoUrl;
    @ApiModelProperty(value = "状态 0禁用,1启用")
    private Integer status;
    @ApiModelProperty(value = "备注")
    private String remark;
    }
