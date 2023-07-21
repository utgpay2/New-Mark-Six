package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;


/**
 * 彩种表
 *
 * @author zlt
 * @date 2023-05-09 19:57:30
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_lottery")
public class Lottery extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "彩种名称")
    private String lotteryName;
    @ApiModelProperty(value = "排序 ASC")
    private Integer sort;
    @ApiModelProperty(value = "是否显示(0隐藏，1显示)")
    private Integer isDisplay;
    @ApiModelProperty(value = "是否结算中(0否，1结算中)")
    private Integer status;
    @ApiModelProperty(value = "图片地址")
    private String picture;
    @ApiModelProperty(value = "下注截止时间 HH:mm:ss")
    private String betDeadlineTime;
    @ApiModelProperty(value = "结算完成时间 HH:mm:ss")
    private String betSettlementTime;
    }
