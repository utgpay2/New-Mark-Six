package com.central.marksix.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("会员信息")
public class SysUserVo implements Serializable {

    @ApiModelProperty("昵称")
    private String username;

    @ApiModelProperty("头像")
    private String headImgUrl;

    @ApiModelProperty("M币余额")
    private BigDecimal mBalance;

}
