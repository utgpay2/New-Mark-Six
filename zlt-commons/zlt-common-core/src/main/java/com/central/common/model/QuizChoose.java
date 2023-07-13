package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

/**
 * 竞猜奖项详情
 *
 * @author zlt
 * @date 2023-05-09 18:42:17
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_quiz_choose")
public class QuizChoose extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "站点id")
    private Long siteId;
    @ApiModelProperty(value = "站点彩种ID")
    private Long siteLotteryId;
    @ApiModelProperty(value = "站点彩种分类(一类)ID")
    private Long siteCategoryId;
    @ApiModelProperty(value = "站点彩种分类(二类)ID")
    private Long quizId;
    @ApiModelProperty(value = "站点彩种分类(三类)ID")
    private Long quizDetailsId;
    @ApiModelProperty(value = "标题")
    private String introduce;
    @ApiModelProperty(value = "顺序")
    private Integer sort;
    @ApiModelProperty(value = "状态 1：启用 2：禁用")
    private Integer status;
    @ApiModelProperty(value = "赔率")
    private Double odds;
    @ApiModelProperty(value = "赔率2(三中二之中三，中特之中二）")
    private Double odds2;
    }
