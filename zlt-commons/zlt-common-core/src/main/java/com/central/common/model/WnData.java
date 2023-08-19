package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

/**
 * 开奖数据
 *
 * @author zlt
 * @date 2023-05-09 18:39:54
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_wn_data")
public class WnData extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "期号")
    private Long qihao;
    @ApiModelProperty(value = "彩种id")
    private Integer lotteryId;
    @ApiModelProperty(value = "开奖号码")
    private String numbers;
    @ApiModelProperty(value = "开奖视频")
    private String videoPath;
    @ApiModelProperty(value = "下一期开奖时间")
    private String nextTime;
    @ApiModelProperty(value = "下一期号")
    private String nextQihao;
    @ApiModelProperty(value = "号码归属年份")
    private Integer year;
    @ApiModelProperty(value = "是否结算完成(0否，1结算完成)")
    private Integer status;
    @ApiModelProperty(value = "是否显示(0隐藏，1显示)")
    private Integer isDisplay;
}
