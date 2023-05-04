package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("online_user")
@ApiModel("在线会员")
public class OnlineUser extends SuperEntity {
    private static final long serialVersionUID = -5886097523705137070L;

    @ApiModelProperty(value = "统计时间段(具体分钟)")
    private String staticsTimes;

    @ApiModelProperty(value = "统计时间段(具体天)")
    private String staticsDay;

    @ApiModelProperty(value = "人数")
    private Integer onlineNum;

    @ApiModelProperty(value = "最大人数")
    private Integer MaxOnlineNum;
}
