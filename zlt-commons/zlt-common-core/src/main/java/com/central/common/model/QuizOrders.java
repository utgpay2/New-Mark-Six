package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @ApiModelProperty(value = "彩种ID")
    private Long lotteryId;
    @ApiModelProperty(value = "站点彩种ID")
    private Long siteLotteryId;
    @ApiModelProperty(value = "站点彩种名称")
    private String lotteryName;
    @ApiModelProperty(value = "站点彩种分类一类ID")
    private Long siteCategoryId;
    @ApiModelProperty(value = "站点彩种分类一类名称")
    private String siteCategoryName;
    @ApiModelProperty(value = "站点彩种分类二类ID")
    private Long quizId;
    @ApiModelProperty(value = "站点彩种分类二类名称")
    private String quizTitle;
    @ApiModelProperty(value = "站点彩种分类三类ID")
    private Long quizDetailsId;
    @ApiModelProperty(value = "站点彩种分类三类名称")
    private String quizDetailsName;
    @ApiModelProperty(value = "投注内容（开奖规则明细ID 如：3172,3173,3174）")
    private String quizChooseIds;
    @ApiModelProperty(value = "投注内容（开奖规则明细名称 如：01,02,03）")
    private String quizIntroduces;
    @ApiModelProperty(value = "赔率")
    private BigDecimal odds;
    @ApiModelProperty(value = "赔率2(三中二之中三，中特之中二）")
    private Double odds2;
    @ApiModelProperty(value = "0 没有子订单,1 有子订单")
    private Integer isSubOrders;
    @ApiModelProperty(value = "订单金额")
    private BigDecimal totalPrice;
    @ApiModelProperty(value = "是否测试账号 0:正式账号 1:测试账号")
    private Integer isTestAccount;
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
    @ApiModelProperty(value = "'1 待开奖,2 已取消,3 中奖,4 未中奖")
    private Integer status;
    @ApiModelProperty(value = "会员ID")
    private Long memberId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty("上级id")
    private Long parentId;
    @ApiModelProperty("上级账号")
    private String parentName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "投注时间")
    private Date bettingTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "撤销时间")
    private Date cancelTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "结算时间")
    private Date settlementTime;
    }
