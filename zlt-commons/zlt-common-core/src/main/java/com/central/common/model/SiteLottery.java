package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 * 彩种表
 *
 * @author zlt
 * @date 2023-05-09 19:57:30
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_site_lottery")
public class SiteLottery extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "彩种ID")
    private Integer lotteryId;
    @ApiModelProperty(value = "商户id")
    private Integer siteId;
    @ApiModelProperty(value = "商户编码")
    private String siteCode;
    @ApiModelProperty(value = "商户名称")
    private String siteName;
    }
