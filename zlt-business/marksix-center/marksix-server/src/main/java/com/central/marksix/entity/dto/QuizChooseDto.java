package com.central.marksix.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 竞猜奖项详情
 *
 * @author zlt
 * @date 2023-05-09 18:42:17
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class QuizChooseDto{
    @ApiModelProperty(value = "号码")
    private String introduce;
    @ApiModelProperty(value = "赔率")
    private Double odds;
    @ApiModelProperty(value = "赔率2 (三中二之中三，中特之中二）")
    private Double odds2;
    @ApiModelProperty(value = "波色")
    private String color;
    }
