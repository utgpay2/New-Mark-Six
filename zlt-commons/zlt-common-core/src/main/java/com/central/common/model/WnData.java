package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Date;

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
public class WnData  {
    private static final long serialVersionUID=1L;
    @TableId
    @ApiModelProperty(value = "开奖ID")
    private Long id;
    @ApiModelProperty(value = "期号")
    private Long qihao;
    @ApiModelProperty(value = "彩种id")
    private Integer lotteryId;
    @ApiModelProperty(value = "开奖号码")
    private String numbers;
    @ApiModelProperty(value = "创建时间")
    private Integer createTime;
    @ApiModelProperty(value = "开奖视频")
    private String videoPath;
    @ApiModelProperty(value = "下一期开奖时间")
    private Integer nextTime;
    @ApiModelProperty(value = "下一期号")
    private String nextQihao;
    @ApiModelProperty(value = "号码归属年份")
    private Integer year;
    @ApiModelProperty(value = "是否结算完成(0否，1结算完成)")
    private Integer status;
    }
