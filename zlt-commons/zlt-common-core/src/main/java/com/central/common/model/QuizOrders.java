package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

/**
 * 
 *
 * @author zlt
 * @date 2023-05-09 18:43:48
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_quiz_orders")
public class QuizOrders extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "订单号")
    private String orderNo;
    @ApiModelProperty(value = "期数")
    private String periods;
    @ApiModelProperty(value = "年份")
    private Integer year;
    @ApiModelProperty("站点id")
    private Long siteId;
    @ApiModelProperty("站点编码")
    private String siteCode;
    @ApiModelProperty("站点名称")
    private String siteName;
    @ApiModelProperty(value = "站点彩种ID")
    private Long siteLotteryId;
    @ApiModelProperty(value = "站点彩种名称")
    private String lotteryName;
    @ApiModelProperty(value = "站点下注分类ID")
    private Long siteCategoryId;
    @ApiModelProperty(value = "站点下注分类名称")
    private Long siteCategoryName;
    @ApiModelProperty(value = "投注内容")
    private String bettingContent;

    @ApiModelProperty(value = "开奖规则主表ID")
    private Long quizId;
    @ApiModelProperty(value = "开奖规则主表标题")
    private String quizTitle;
    @ApiModelProperty(value = "开奖规则明细ID")
    private Long quizChooseId;
    @ApiModelProperty(value = "开奖规则明细名称")
    private String quizIntroduce;
    @ApiModelProperty(value = "赔率")
    private BigDecimal odds;
    @ApiModelProperty(value = "订单金额")
    private BigDecimal totalPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public BigDecimal getTotalPrice() {
        return totalPrice == null ? BigDecimal.ZERO.setScale(2) : totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    @ApiModelProperty(value = "赢取金额")
    private BigDecimal winMount;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public BigDecimal getWinMount() {
        return winMount == null ? BigDecimal.ZERO.setScale(2) : winMount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    @ApiModelProperty(value = "输赢金额")
    private BigDecimal winLoseAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public BigDecimal getWinLoseAmount() {
        return winLoseAmount == null ? BigDecimal.ZERO.setScale(2) : winLoseAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    @ApiModelProperty(value = "注数")
    private Integer units;
    @ApiModelProperty(value = "'1 待开奖,2 已取消,3 中奖,4 未中奖,5 追号中,6 追号完成,7 追号取消,8 追号用户取消,9 其他")
    private Integer status;
    @ApiModelProperty(value = "会员ID")
    private Long memberId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty("上级id")
    private Long parentId;
    @ApiModelProperty("上级账号")
    private String parentName;
    }
