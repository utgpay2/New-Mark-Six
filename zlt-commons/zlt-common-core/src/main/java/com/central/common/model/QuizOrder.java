package com.central.common.model;

import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * @author zlt
 * @date 2023-05-09 18:43:00
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_quiz_order")
public class QuizOrder extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "父级订单号")
    private String fOrder;
    @ApiModelProperty(value = "期数")
    private Integer periods;
    @ApiModelProperty(value = "类型")
    private Integer quizId;
    @ApiModelProperty(value = "订单总额")
    private BigDecimal totalPrice;
    @ApiModelProperty(value = "赢取金额")
    private BigDecimal winMount;
    @ApiModelProperty(value = "输赢金额")
    private BigDecimal winLoseAmount;
    @ApiModelProperty(value = "总注数")
    private Integer units;
    @ApiModelProperty(value = "年份")
    private Integer year;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    @ApiModelProperty(value = "1 待开奖,2 已取消,3 中奖,4 未中奖,5 追号中,6 追号完成,7 追号取消,8 追号用户取消,9 其他")
    private Integer status;
    @ApiModelProperty(value = "会员ID")
    private Integer memberId;
    @ApiModelProperty(value = "彩种ID")
    private Integer lotteryId;
    }
