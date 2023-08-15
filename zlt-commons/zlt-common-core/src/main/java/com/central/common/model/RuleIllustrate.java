package com.central.common.model;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("mks_rule_illustrate")
@ApiModel("玩法说明")
public class RuleIllustrate extends SuperEntity {

    @ApiModelProperty(value = "彩种ID")
    private Integer lotteryId;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "说明")
    private String illustrate;

}
