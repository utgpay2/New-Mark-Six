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
    @ApiModelProperty(value = "开奖种类三类ID")
    private Long quizDetailsId;
    @ApiModelProperty(value = "标题")
    private String introduce;
    @ApiModelProperty(value = "顺序")
    private Integer sort;
    @ApiModelProperty(value = "状态 1：启用 2：禁用")
    private Integer status;
    @ApiModelProperty(value = "赔率")
    private Double odds;
    }
