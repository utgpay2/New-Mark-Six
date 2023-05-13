package com.central.common.model;

import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Date;

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
    @ApiModelProperty(value = "开奖规则主表ID")
    private Integer quizId;
    @ApiModelProperty(value = "介绍")
    private String introduce;
    @ApiModelProperty(value = "奖项")
    private String number;
    @ApiModelProperty(value = "颜色1:红 2：蓝 3：绿  4：橙")
    private Integer color;
    @ApiModelProperty(value = "顺序")
    private Integer sort;
    @ApiModelProperty(value = "状态 1：启用 2：禁用 3：淘料专用")
    private Integer status;
    @ApiModelProperty(value = "赔率")
    private Double odds;
    @ApiModelProperty(value = "标记")
    private String headline;
    @ApiModelProperty(value = "位置")
    private String location;
    }
