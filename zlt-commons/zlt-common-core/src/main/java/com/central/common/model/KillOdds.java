package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 设置杀率
 *
 * @author yixiu
 * @date 2023-02-03 16:32:41
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_kill_odds")
public class KillOdds extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "杀率，0-100 100为百分百杀 0为不设置杀率")
    private Integer killOdds;
    @ApiModelProperty(value = "彩种ID")
    private Integer lotteryId;

    }
