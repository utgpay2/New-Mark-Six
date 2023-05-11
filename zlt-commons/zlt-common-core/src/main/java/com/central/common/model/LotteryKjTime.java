package com.central.common.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 开奖时间
 *
 * @author zlt
 * @date 2023-05-09 19:59:03
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_lottery_kj_time")
public class LotteryKjTime{
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "彩种ID")
    private Integer lotteryId;
    @ApiModelProperty(value = "当天期号/完整期号")
    private String qihao;
    @TableId
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    }
