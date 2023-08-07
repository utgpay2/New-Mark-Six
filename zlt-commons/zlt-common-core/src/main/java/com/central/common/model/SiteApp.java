package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;

/**
 * 商户app更新配置
 *
 * @author yixiu
 * @date 2023-02-21 19:46:07
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("kpn_site_app")
public class SiteApp extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "商户id")
    private Integer siteId;
    @ApiModelProperty(value = "商户编码")
    private String siteCode;
    @ApiModelProperty(value = "商户名称")
    private String siteName;
    @ApiModelProperty(value = "终端类型 android,ios")
    private String type;
    @ApiModelProperty(value = "版本名称")
    private String versionName;
    @ApiModelProperty(value = "版本号")
    private String versionNum;
    @ApiModelProperty(value = "下载地址")
    private String downloadUrl;
    @ApiModelProperty(value = "是否强制更新 0不强制,1强制")
    private Integer isForce;
    @Max(value = 100,message = "备注最大长度不能超过100！")
    @ApiModelProperty(value = "备注")
    private String remark;
    }
