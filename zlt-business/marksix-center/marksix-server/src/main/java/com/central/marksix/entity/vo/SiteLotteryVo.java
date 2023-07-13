package com.central.marksix.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

/**
 * 彩种表
 *
 * @author zlt
 * @date 2023-05-09 19:57:30
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class SiteLotteryVo {
    @ApiModelProperty(value = "站点彩种ID")
    private Long id;
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "彩种ID")
    private Integer lotteryId;
    @ApiModelProperty(value = "站点id")
    private Integer siteId;
    @ApiModelProperty(value = "站点编码")
    private String siteCode;
    @ApiModelProperty(value = "站点名称")
    private String siteName;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "彩种名称")
    private String lotteryName;
    @ApiModelProperty(value = "图片地址")
    private String picture;
    @ApiModelProperty(value = "下注截止时间")
    private Time betDeadlineTime;
    @ApiModelProperty(value = "结算完成时间")
    private Time betSettlementTime;
    @ApiModelProperty(value = "是否结算中(0否，1结算中)")
    private Integer status;
    @ApiModelProperty(value = "是否显示(0隐藏，1显示)")
    private Integer isDisplay;
    }
