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
    @ApiModelProperty(value = "")
    private Long id;
    @ApiModelProperty(value = "")
    private Long qihao;
    @ApiModelProperty(value = "")
    private Integer lotteryId;
    @ApiModelProperty(value = "")
    private String numbers;
    @ApiModelProperty(value = "")
    private Integer createTime;
    @ApiModelProperty(value = "")
    private String videoPath;
    @ApiModelProperty(value = "")
    private Integer nextTime;
    @ApiModelProperty(value = "")
    private String nextQihao;
    @ApiModelProperty(value = "")
    private Integer year;
    }
