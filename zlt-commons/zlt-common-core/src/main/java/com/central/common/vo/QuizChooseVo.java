package com.central.common.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.NumberAttributes;
import com.central.common.model.SuperEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 竞猜奖项详情
 *
 * @author zlt
 * @date 2023-05-09 18:42:17
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class QuizChooseVo{

    @ApiModelProperty(value = "奖项详情")
    private Long id;
    @ApiModelProperty(value = "站点彩种分类三类ID")
    private Integer quizDetailsId;
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
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    @ApiModelProperty(value = "号码属性集合")
    List<NumberAttributes> numberList;
    }
