package com.central.common.model;

import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 站点客服配置
 *
 * @author yixiu
 * @date 2023-02-21 19:46:07
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_serve")
public class KpnSiteServe extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "站点id")
    private Long siteId;
    @ApiModelProperty(value = "站点编码")
    private String siteCode;
    @ApiModelProperty(value = "站点名称")
    private String siteName;
    @ApiModelProperty(value = "平台(Skype,QQ,Wechat,Telegram,WhatsApp,手机号)")
    private String platform;
    @ApiModelProperty(value = "平台客服账号")
    private String serveAccount;
    @ApiModelProperty(value = "状态 0关闭,1启用")
    private Integer status;
    @ApiModelProperty(value = "pc图标地址")
    private String pcIconUrl;
    @ApiModelProperty(value = "app图标地址")
    private String appIconUrl;
    }
