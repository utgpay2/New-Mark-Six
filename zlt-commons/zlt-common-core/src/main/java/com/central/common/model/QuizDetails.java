package com.central.common.model;

import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 竞猜分类
 *
 * @author zlt
 * @date 2023-05-30 13:00:21
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_quiz_details")
public class QuizDetails extends SuperEntity {
    private static final long serialVersionUID=1L;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "图片")
    private String picture;
    @ApiModelProperty(value = "状态 1：启用 0：禁用")
    private Integer status;
    @ApiModelProperty(value = "开奖分类二类ID")
    private Long quizId;
    @ApiModelProperty(value = "顺序")
    private Integer sort;
    }
